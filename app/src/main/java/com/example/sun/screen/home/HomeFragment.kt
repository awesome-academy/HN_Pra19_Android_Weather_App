package com.example.sun.screen.home

import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sun.R
import com.example.sun.data.model.CurrentWeather
import com.example.sun.data.repository.CurrentWeatherRepository
import com.example.sun.data.repository.source.local.CurrentWeatherLocalDataSource
import com.example.sun.data.repository.source.remote.CurrentWeatherRemoteDataSource
import com.example.sun.databinding.FragmentHomeBinding
import com.example.sun.utils.base.BaseFragment
import com.example.sun.screen.detail.DetailFragment
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContract.View {

    private lateinit var mHomePresenter: HomePresenter

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun initData() {
        mHomePresenter = HomePresenter(
            CurrentWeatherRepository.getInstance(
                CurrentWeatherRemoteDataSource.getInstance(),
                CurrentWeatherLocalDataSource.getInstance()
            )
        )
        mHomePresenter.setView(this)
        mHomePresenter.getCurrentWeather()
    }

    override fun initView() {
        viewBinding.btnForecastReport.setOnClickListener {
            Log.i("onViewCreated", "setOnClickListener")
            onForecastClicked()
        }
    }

    private fun onForecastClicked() {
        Log.i("HomeFragment", "parentFragmentManager hashCode: ${parentFragmentManager.hashCode()}")
        parentFragmentManager.beginTransaction()
            .replace(R.id.layoutContainer, DetailFragment.newInstance())
            .addToBackStack(null)
            .commit()
        Log.i("onBtnBackClicked", "Back stack entry count when onForecastClicked: ${parentFragmentManager.backStackEntryCount}")
    }
    override fun onGetCurrentWeatherSuccess(currentWeather: CurrentWeather) {
        Log.e("CurrentWeather", currentWeather.toString())
        viewBinding.apply {
            tvCurrentDay.text = currentWeather.lastUpdated
            tvCurrentTemperature.text = currentWeather.currentTemperature.toString()
            tvCurrentText.text = currentWeather.weatherStatus
            tvCurrentWindSpeed.text = currentWeather.windSpeed.toString()
            tvCurrentHumidity.text = currentWeather.humidity.toString()
            context?.let { Glide.with(it) }?.load("https:" + currentWeather.iconWeather)
                ?.into(ivCurrentWeather)
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
