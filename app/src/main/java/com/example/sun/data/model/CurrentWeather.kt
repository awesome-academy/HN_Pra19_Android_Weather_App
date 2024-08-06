package com.example.sun.data.model

import java.io.Serializable

data class CurrentWeather(
    var name: String = "",
    var lastUpdated: String = "",
    var currentTemperature: Double = 0.0,
    var weatherStatus: String = "",
    var iconWeather: String = "",
    var windSpeed: Double = 0.0,
    var humidity: Int = 0,
    var percentCloud: Int = 0
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 123
    }
}
