package com.example.sun.data.model

import android.util.Log

class Api {
    companion object {
        val Apikey = "de0596946589458dbc891932240708"
        var isInitialized = false
        var Location = "Saigon"
            set(value) {
                field = value

                updateUrls()
                Log.i("Api", "$Location")

                if (isInitialized) {
                    onLocationKeyUpdated()
                }
                isInitialized = true
            }

        var apiForecastHour: String = ""
            private set
        var apiForecastDay: String = ""
            private set

        init {
            updateUrls()
        }

        private fun updateUrls() {
            apiForecastHour = "https://api.weatherapi.com/v1/forecast.json?key=$Apikey&q=$Location&hours=12"
            apiForecastDay = "https://api.weatherapi.com/v1/forecast.json?key=$Apikey&q=$Location&days=14"
        }
        var onLocationKeyUpdated: () -> Unit = {}
    }
}
