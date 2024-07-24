package com.example.sun.data.repository.source.remote.fetchJson

import com.example.sun.data.model.CurrentWeather
import com.example.sun.utils.CurrentWeatherEntry.CURRENT_TEMPERATURE
import com.example.sun.utils.CurrentWeatherEntry.HUMIDITY
import com.example.sun.utils.CurrentWeatherEntry.ICON_WEATHER
import com.example.sun.utils.CurrentWeatherEntry.PERCENT_CLOUD
import com.example.sun.utils.CurrentWeatherEntry.WEATHER_STATUS
import com.example.sun.utils.CurrentWeatherEntry.WIND_SPEED
import org.json.JSONObject

class ParseJson {

    fun currentWeatherParseJson(jsonObject: JSONObject) = CurrentWeather().apply {
        jsonObject.let {
            currentTemperature = it.getDouble(CURRENT_TEMPERATURE)
            weatherStatus = it.getString(WEATHER_STATUS)
            iconWeather = it.getString(ICON_WEATHER)
            windSpeed = it.getDouble(WIND_SPEED)
            humidity = it.getInt(HUMIDITY)
            percentCloud = it.getInt(PERCENT_CLOUD)
        }
    }
}
