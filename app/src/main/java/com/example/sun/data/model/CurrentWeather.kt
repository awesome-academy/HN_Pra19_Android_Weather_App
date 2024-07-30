package com.example.sun.data.model

data class CurrentWeather(
    var currentTemperature: Double = 0.0,
    var weatherStatus: String = "",
    var iconWeather: String = "",
    var windSpeed: Double = 0.0,
    var humidity: Int = 0,
    var percentCloud: Int = 0
)
