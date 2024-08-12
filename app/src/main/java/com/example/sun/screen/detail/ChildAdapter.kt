package com.example.sun.screen.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sun.R
import com.example.sun.data.model.DataType
import com.example.sun.data.model.ForecastDay
import com.example.sun.data.model.ForecastHour
import com.example.sun.databinding.ForecastDayBinding
import com.example.sun.databinding.ForecastHourBinding
import com.example.sun.databinding.FragmentAppBarBinding

class ChildAdapter(private val ViewType : Int
                   ,private val ForecastHourList : List<ForecastHour> = listOf()
                    ,private val ForecastDayList : List<ForecastDay> = listOf()):
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ForecastHourHolder(private val binding: ForecastHourBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bindForecastHour(foreHourItem: ForecastHour){
                binding.forecastHour.text = foreHourItem.time.substring(11,16)
                Glide.with(binding.root.context)
                    .load("https:${foreHourItem.icon}")
                    .into(binding.forecastImg)
                binding.forecastT.text = foreHourItem.avgtemp_c
                if(foreHourItem.local_time.toInt() == foreHourItem.time.substring(11,13).toInt()){
                    Log.i(" foreHourItem  ${foreHourItem.local_time.toInt()} ","${foreHourItem.time.substring(11,13).toInt()}")
                    binding.freHour.setBackgroundResource(R.drawable.color_background_display_field)

                }else{
                    binding.freHour.setBackgroundResource(android.R.color.transparent)
                }


            }

        }
    inner class ForecastDayHolder(private val binding : ForecastDayBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bindForecastDay(foreDayItem: ForecastDay){
                binding.tvDay.text = foreDayItem.date.substring(5,10)
                binding.tvDay2.text = foreDayItem.temp_avg
                Glide.with(binding.root.context)
                    .load("https:${foreDayItem.icon}")
                    .into(binding.imgDay)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            DataType.FORECAST_HOUR ->{
                val binding = ForecastHourBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ForecastHourHolder(binding)
            }
            DataType.FORECAST_DAY ->{
                val binding = ForecastDayBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ForecastDayHolder(binding)
            }
            else ->throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return ViewType
    }
    override fun getItemCount(): Int {
        return  when(ViewType){
            DataType.FORECAST_HOUR ->{
                ForecastHourList.size
            }
            DataType.FORECAST_DAY ->{
                ForecastDayList.size
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ForecastHourHolder -> {
                holder.bindForecastHour(ForecastHourList[position])
            }
            is ForecastDayHolder -> {
                holder.bindForecastDay(ForecastDayList[position])
            }
        }
    }
}
