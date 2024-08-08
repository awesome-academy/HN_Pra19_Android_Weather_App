package com.example.sun.screen.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sun.data.model.Data
import com.example.sun.data.model.DataType
import com.example.sun.data.model.ForecastDay
import com.example.sun.data.model.ForecastHour
import com.example.sun.databinding.FragmentDetailItemBinding

class DetailAdapter(private val weather: List<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class DetaiHolder(private val binding : FragmentDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bindForecastHour(forecasthourList : List<ForecastHour>) {
        binding.childRecyclerView.setHasFixedSize(true)
        binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context,RecyclerView.HORIZONTAL,false)
        val adapter = ChildAdapter(DataType.FORECAST_HOUR, ForecastHourList = forecasthourList)
        binding.childRecyclerView.adapter = adapter
        binding.textA.text = "Today"
        binding.textB.text = forecasthourList[0].time.substring(5,10)
        val local_time = forecasthourList[0].local_time.toInt()
        binding.childRecyclerView.scrollToPosition(local_time)
        }

    fun bindForecastDay(forecastDayList: List<ForecastDay>) {
        binding.childRecyclerView.setHasFixedSize(true)
        binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
        val adapter = ChildAdapter(DataType.FORECAST_DAY, ForecastDayList = forecastDayList)
        binding.childRecyclerView.adapter = adapter
        binding.textA.text = "Next Forecast"
        binding.textB.text = "Temp"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FragmentDetailItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetaiHolder(binding)
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (weather[position]) {
            is Data.ForecastHourData -> DataType.FORECAST_HOUR
            is Data.ForecastDayData -> DataType.FORECAST_DAY
            else -> throw IllegalArgumentException("Invalid")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = weather[position]
        when (data) {
            is Data.ForecastHourData -> {
                if (holder is DetaiHolder) {
                    holder.bindForecastHour(data.forecastHourList)
                }
            }
            is Data.ForecastDayData -> {
                if (holder is DetaiHolder) {
                    holder.bindForecastDay(data.forecastDayList)
                }
            }
            else -> throw IllegalArgumentException("Invalid")
        }
    }
}
