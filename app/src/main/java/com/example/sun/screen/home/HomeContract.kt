package com.example.sun.screen.home

import com.example.sun.data.model.CurrentWeather
import com.example.sun.utils.base.BasePresenter

interface HomeContract {

    interface View {
        fun onGetCurrentWeatherSuccess(currentWeather: CurrentWeather)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getCurrentWeather()
    }
}
