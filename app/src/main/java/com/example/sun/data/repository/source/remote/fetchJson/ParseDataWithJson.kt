package com.example.sun.data.repository.source.remote.fetchJson

import android.util.Log
import com.example.sun.data.model.CurrentWeather
import org.json.JSONException
import org.json.JSONObject

class ParseDataWithJson {
    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): CurrentWeather? {
        return try {
            val weatherObject = jsonObject?.getJSONObject(keyEntity)
            parseJsonToObject(weatherObject)
        } catch (e: JSONException) {
            Log.e("ParseDataWithJson", "parseJsonToData: ", e)
            null
        }
    }

    private fun parseJsonToObject(jsonObject: JSONObject?): CurrentWeather? {
        try {
            jsonObject?.let {
                return ParseJson().currentWeatherParseJson(it)
            }
        } catch (e: JSONException) {
            Log.e("ParseDataWithJson", "parseJsonToData: ", e)
        }
        return null
    }
}
