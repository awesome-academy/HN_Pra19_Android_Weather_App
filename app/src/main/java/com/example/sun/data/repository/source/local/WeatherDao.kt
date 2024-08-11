package com.example.sun.data.repository.source.local
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.sun.data.model.ForecastDay
import com.example.sun.data.model.ForecastHour
import com.example.sun.data.repository.source.local.db.WeatherDatabaseHelper
import com.example.sun.utils.ForecastDayEntry
import com.example.sun.utils.ForecastHourEntry

class ForecastDayDao(context: Context) {

    private val dbHelper = WeatherDatabaseHelper(context)

    fun insertForecastDay(forecastDay: ForecastDay) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ForecastDayEntry.COLUMN_NAME_DATE, forecastDay.date)
            put(ForecastDayEntry.COLUMN_NAME_TEMP_AVG, forecastDay.temp_avg)
            put(ForecastDayEntry.COLUMN_NAME_ICON, forecastDay.icon)
        }
        db.insert(ForecastDayEntry.TABLE_NAME, null, values)
    }

    fun getForecastDays(): List<ForecastDay> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            ForecastDayEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val forecastDays = mutableListOf<ForecastDay>()
        cursor.use {
            while (it.moveToNext()) {
                val date = it.getString(it.getColumnIndexOrThrow(ForecastDayEntry.COLUMN_NAME_DATE))
                val avgtemp = it.getString(it.getColumnIndexOrThrow(ForecastDayEntry.COLUMN_NAME_TEMP_AVG))
                val icon = it.getString(it.getColumnIndexOrThrow(ForecastDayEntry.COLUMN_NAME_ICON))
                forecastDays.add(ForecastDay(date, avgtemp, icon))
            }
        }
        return forecastDays
    }

    fun deleteAllForecastDays() {
        val db = dbHelper.writableDatabase
        db.delete(ForecastDayEntry.TABLE_NAME, null, null)
    }
}
class ForecastHourDao(context: Context) {

    private val dbHelper = WeatherDatabaseHelper(context)

    fun insertForecastHour(forecastHour: ForecastHour) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ForecastHourEntry.COLUMN_NAME_LOCAL_TIME,forecastHour.local_time)
            put(ForecastHourEntry.COLUMN_NAME_TIME, forecastHour.time)
            put(ForecastHourEntry.COLUMN_NAME_TEMP_AVG, forecastHour.avgtemp_c)
            put(ForecastHourEntry.COLUMN_NAME_ICON, forecastHour.icon)
        }
        db.insert(ForecastHourEntry.TABLE_NAME, null, values)
    }

    fun getForecastHours(): List<ForecastHour> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            ForecastHourEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val forecastHours = mutableListOf<ForecastHour>()
        cursor.use {
            while (it.moveToNext()) {
                val local_time = it.getString(it.getColumnIndexOrThrow(ForecastHourEntry.COLUMN_NAME_LOCAL_TIME))
                val time = it.getString(it.getColumnIndexOrThrow(ForecastHourEntry.COLUMN_NAME_TIME))
                val avgtemp_c = it.getString(it.getColumnIndexOrThrow(ForecastHourEntry.COLUMN_NAME_TEMP_AVG))
                val icon = it.getString(it.getColumnIndexOrThrow(ForecastHourEntry.COLUMN_NAME_ICON))
                forecastHours.add(ForecastHour(local_time,time, avgtemp_c, icon))
            }
        }
        return forecastHours
    }

    fun deleteAllForecastHours() {
        val db = dbHelper.writableDatabase
        db.delete(ForecastHourEntry.TABLE_NAME, null, null)
    }
}