package com.example.varsity24

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.varsity24.Models.WeatherModel.DailyForecasts
import com.example.varsity24.Models.WeatherModel.Weather
import com.example.varsity24.ApiClient.WeatherApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherFragment : Fragment() {

    private lateinit var currentTemp: TextView
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        currentTemp = view.findViewById(R.id.txtWeather)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            WeatherApiClient.buildService()
                .getWeatherData("ivoeQDGf10Atq3powOZqbRibOrPvUA93")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    onResponse(response)
                }, { t ->
                    onFailure(t)
                })
        )
    }

    private fun onResponse(response: Weather) {
        Log.d("[news]", response.toString())
        val dailyForecasts: ArrayList<DailyForecasts> = response.DailyForecasts
        val temperatureValue = dailyForecasts[0].Temperature

        currentTemp.text = "Temperature: $temperatureValue"
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
        t.printStackTrace()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}