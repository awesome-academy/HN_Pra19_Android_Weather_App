package com.example.sun.data.repository.source

import com.example.sun.data.model.CurrentWeather
import com.example.sun.data.repository.source.remote.OnResultListener

interface CurrentWeatherDataSource {

    interface Local {
        fun getCurrentWeatherLocal(listener: OnResultListener<CurrentWeather>)
    }

    interface Remote {
        fun getCurrentWeather(listener: OnResultListener<CurrentWeather>)
    }
}
