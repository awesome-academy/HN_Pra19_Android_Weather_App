package com.example.sun.data.repository.source.local

import com.example.sun.data.model.CurrentWeather
import com.example.sun.data.repository.source.CurrentWeatherDataSource
import com.example.sun.data.repository.source.remote.OnResultListener

class CurrentWeatherLocalDataSource : CurrentWeatherDataSource.Local {
    override fun getCurrentWeatherLocal(listener: OnResultListener<MutableList<CurrentWeather>>) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: CurrentWeatherLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: CurrentWeatherLocalDataSource().also { instance = it }
        }
    }
}
