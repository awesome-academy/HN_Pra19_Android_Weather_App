package com.example.sun.screen.home

import com.example.sun.data.model.CurrentWeather
import com.example.sun.data.repository.CurrentWeatherRepository
import com.example.sun.data.repository.source.remote.OnResultListener
import java.lang.Exception

class HomePresenter(private val currentWeatherRepository: CurrentWeatherRepository) : HomeContract.Presenter {

    private var mView: HomeContract.View? = null

    override fun getCurrentWeather() {
        currentWeatherRepository?.getCurrentWeather(object : OnResultListener<CurrentWeather> {
            override fun onSuccess(data: CurrentWeather) {
                mView?.onGetCurrentWeatherSuccess(data)
            }

            override fun onError(exception: Exception?) {
                mView?.onError(exception)
            }
        })
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: HomeContract.View?) {
        this.mView = view
    }
}
