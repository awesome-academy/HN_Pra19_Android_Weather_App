package com.example.sun.screen.detail

import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sun.R
import com.example.sun.data.model.Api
import com.example.sun.data.model.Data
import com.example.sun.data.repository.source.remote.fetchJson.fetchForecastDay
import com.example.sun.data.repository.source.remote.fetchJson.fetchForecastHour
import com.example.sun.databinding.FragmentDetailBinding
import com.example.sun.utils.base.BaseFragment
import java.util.concurrent.Executors

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var mList: ArrayList<Data>
    private val executor = Executors.newSingleThreadExecutor()

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater)
    }
    override fun initData() {
    }
    override fun initView() {
        viewBinding.mainRecyclerview.setHasFixedSize(true)
        viewBinding.mainRecyclerview.layoutManager = LinearLayoutManager(context)

        mList = ArrayList()
        updateWeatherHome()

        childFragmentManager.beginTransaction()
            .replace(R.id.appBarFragment, AppBarFragment())
            .commit()

        Log.i("DetailFragment", "parentFragmentManager hashCode: ${parentFragmentManager.hashCode()}")
    }

    private fun updateWeatherHome() {
        Log.d("DetailFragment", "updateWeatherHome called")
        executor.execute {
            try {
                val forecastHour = fetchForecastHour(Api.apiForecastHour)
                val forecastDay = fetchForecastDay(Api.apiForecastDay)
                val dataList = mutableListOf<Data>()

                dataList.add(Data.ForecastHourData(forecastHour))
                dataList.add(Data.ForecastDayData(forecastDay))

                activity?.runOnUiThread {
                    mList = ArrayList(dataList)
                    val adapter = DetailAdapter(mList)
                    viewBinding.mainRecyclerview.adapter = adapter

                }
            } catch (e: Exception) {
                Log.e("DetailFragment", "Error updating weather home", e)
            }
        }
                }
    companion object {
        fun newInstance() = DetailFragment()
    }
}
