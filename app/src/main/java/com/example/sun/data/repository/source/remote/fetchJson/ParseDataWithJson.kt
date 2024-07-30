package com.example.sun.data.repository.source.remote.fetchJson

import android.util.Log
import com.example.sun.data.model.CurrentWeather
import org.json.JSONException
import org.json.JSONObject

class ParseDataWithJson {
    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): MutableList<CurrentWeather> {
        val data = mutableListOf<CurrentWeather>()
        try {
            val jsonArray = jsonObject?.getJSONArray(keyEntity)
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val item = parseJsonToObject(jsonArray?.getJSONObject(i))
                item?.let {
                    data.add(it)
                }
            }
        } catch (e: JSONException) {
            Log.e("ParseDataWithJson", "parseJsonToData: ", e)
        }
        return data
    }

    private fun parseJsonToObject(jsonObject: JSONObject?): CurrentWeather? {
        try {
            jsonObject?.let {
                return ParseJson().currentWeatherParseJson(it)
            }
        } catch (e: JSONException) {
            Log.e("ParseDataWithJson", "parseJsonToData: ", e)
        }
        return null
    }
}
