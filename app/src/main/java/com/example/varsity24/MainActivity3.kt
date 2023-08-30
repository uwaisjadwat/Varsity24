package com.example.varsity24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.varsity24.Models.WeatherModel.DailyForecasts
import com.example.varsity24.Models.WeatherModel.Weather
import com.example.varsity24.Weather.WeatherApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

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
        val dailyForecasts: List<DailyForecasts> = response.DailyForecasts
        val currentTemp = findViewById<TextView>(R.id.txtWeather)
        val temperatureValue = dailyForecasts[0].Temperature

        currentTemp.text = "Temperature: $temperatureValue"

    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        t.printStackTrace()
    }


    }
