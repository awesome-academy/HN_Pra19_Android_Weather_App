package com.example.sun.data.model

sealed class Data {
    data class ForecastHourData(val forecastHourList: List<ForecastHour>) : Data()
    data class ForecastDayData(val forecastDayList: List<ForecastDay>) : Data()
}
data class ForecastDay(
    var date: String,
    var temp_avg: String,
    var icon: String
)
data class ForecastHour(
    var local_time: String,
    var time: String,
    var avgtemp_c: String,
    var icon: String
)