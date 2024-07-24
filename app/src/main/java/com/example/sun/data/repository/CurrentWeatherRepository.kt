package com.example.sun.data.repository

import com.example.sun.data.model.CurrentWeather
import com.example.sun.data.repository.source.CurrentWeatherDataSource
import com.example.sun.data.repository.source.remote.OnResultListener

class CurrentWeatherRepository(
    private val remote: CurrentWeatherDataSource.Remote,
    private val local: CurrentWeatherDataSource.Local
) : CurrentWeatherDataSource.Local, CurrentWeatherDataSource.Remote {
    override fun getCurrentWeatherLocal(listener: OnResultListener<MutableList<CurrentWeather>>) {
        TODO("Not yet implemented")
    }

    override fun getCurrentWeather(listener: OnResultListener<MutableList<CurrentWeather>>) {
        remote.getCurrentWeather(listener)
    }

    companion object {
        private var instant: CurrentWeatherRepository? = null

        fun getInstance(
            remote: CurrentWeatherDataSource.Remote,
            local: CurrentWeatherDataSource.Local
        ) = synchronized(this) {
            instant ?: CurrentWeatherRepository(remote, local).also { instant = it }
        }
    }
}
