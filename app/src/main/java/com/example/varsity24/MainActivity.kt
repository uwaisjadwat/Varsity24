package com.example.varsity24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.varsity24.Currency.CurrencyApiClient
import com.example.varsity24.Models.CurrencyModel.Curency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currencyValueText = findViewById<TextView>(R.id.currency_value)



        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            CurrencyApiClient.buildService().getCurrency("USD", "ZAR", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    onResponse(response)
                }, { t ->
                    onFailure(t)
                })
        )

        val news = findViewById<Button>(R.id.btn_news)
        val weather = findViewById<Button>(R.id.btnWeather)

        news.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }

        weather.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)

        }



    }



    private fun onResponse(response: Curency) {
        val newAmount = response.newAmount
        val currencyValueText = findViewById<TextView>(R.id.currency_value)
        currencyValueText.text = "ZAR = " + newAmount.toString()



        Toast.makeText(this, "$newAmount", Toast.LENGTH_SHORT).show()
        Log.i("[Success]", "$response")
    }



    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
        t.message?.let { Log.e("[Failed]", it) }
    }
}