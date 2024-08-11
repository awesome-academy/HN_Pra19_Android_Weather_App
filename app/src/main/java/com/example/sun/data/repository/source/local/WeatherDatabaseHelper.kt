package com.example.sun.data.repository.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sun.utils.ForecastDayEntry
import com.example.sun.utils.ForecastHourEntry

class WeatherDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_FORECAST_DAY_TABLE)
        db.execSQL(SQL_CREATE_FORECAST_HOUR_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_FORECAST_DAY_TABLE)
        db.execSQL(SQL_DELETE_FORECAST_HOUR_TABLE)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "Weather.db"


        private const val SQL_CREATE_FORECAST_DAY_TABLE =
            "CREATE TABLE ${ForecastDayEntry.TABLE_NAME} (" +
                    "${ForecastDayEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                    "${ForecastDayEntry.COLUMN_NAME_DATE} TEXT," +
                    "${ForecastDayEntry.COLUMN_NAME_TEMP_AVG} TEXT," +
                    "${ForecastDayEntry.COLUMN_NAME_ICON} TEXT)"

        private const val SQL_CREATE_FORECAST_HOUR_TABLE =
            "CREATE TABLE ${ForecastHourEntry.TABLE_NAME} (" +
                    "${ForecastHourEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                    "${ForecastHourEntry.COLUMN_NAME_LOCAL_TIME} TEXT," +
                    "${ForecastHourEntry.COLUMN_NAME_TIME} TEXT," +
                    "${ForecastHourEntry.COLUMN_NAME_TEMP_AVG} TEXT," +
                    "${ForecastHourEntry.COLUMN_NAME_ICON} TEXT)"

        private const val SQL_DELETE_FORECAST_DAY_TABLE = "DROP TABLE IF EXISTS ${ForecastDayEntry.TABLE_NAME}"
        private const val SQL_DELETE_FORECAST_HOUR_TABLE = "DROP TABLE IF EXISTS ${ForecastHourEntry.TABLE_NAME}"
    }
}