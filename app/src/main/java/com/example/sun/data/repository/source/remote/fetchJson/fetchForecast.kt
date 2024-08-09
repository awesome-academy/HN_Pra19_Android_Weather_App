package com.example.sun.data.repository.source.remote.fetchJson
import com.example.sun.data.model.ForecastDay
import com.example.sun.data.model.ForecastHour

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

fun fetchForecastDay( apiUrl: String): List<ForecastDay> {
    val url = URL(apiUrl)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"


    return try {
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = connection.inputStream.bufferedReader().use(BufferedReader::readText)
            val gson = Gson()
            val jsonObject = gson.fromJson(response, JsonObject::class.java)
            val forecast = jsonObject.getAsJsonObject("forecast")
            val forecastDays = forecast.getAsJsonArray("forecastday")
            if (forecastDays != null && forecastDays.size() > 0) {
                val forecastList = mutableListOf<ForecastDay>()

                for (jsonElement in forecastDays) {
                    val forecastJson = jsonElement.asJsonObject
                    val dateDay = forecastJson.get("date").asString
                    val day = forecastJson.getAsJsonObject("day")
                    val temp = day.get("avgtemp_c").asString + "°C"
                    val condition = day.getAsJsonObject("condition")
                    val icon = condition.get("icon").asString

                    val forecastDay = ForecastDay(dateDay, temp, icon)
                    forecastList.add(forecastDay)

                }
                forecastList
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    } finally {
        connection.disconnect()
    }
}

fun fetchForecastHour( apiUrl: String): List<ForecastHour> {
    val url = URL(apiUrl)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    return try {
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = connection.inputStream.bufferedReader().use(BufferedReader::readText)
            val gson = Gson()
            val jsonObject = gson.fromJson(response, JsonObject::class.java)

            val forecast = jsonObject.getAsJsonObject("forecast")
            val forecastDays = forecast.getAsJsonArray("forecastday")
            if (forecastDays != null && forecastDays.size() > 0) {
                val forecastHourList = mutableListOf<ForecastHour>()

                for (jsonElement in forecastDays) {
                    val forecastDayJson = jsonElement.asJsonObject
                    val hours = forecastDayJson.getAsJsonArray("hour")
                    for (hourElement in hours) {
                        val location = jsonObject.getAsJsonObject("location")
                        val localTime = location.get("localtime").asString.substring(11,13)
                        val hourJson = hourElement.asJsonObject
                        val time = hourJson.get("time").asString
                        val tempAvg = hourJson.get("temp_c").asString + "°C"
                        val condition = hourJson.getAsJsonObject("condition")
                        val icon = condition.get("icon").asString

                        val forecastHour = ForecastHour(localTime, time, tempAvg, icon)
                        forecastHourList.add(forecastHour)

                    }
                }
                forecastHourList
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    } finally {
        connection.disconnect()
    }
}
