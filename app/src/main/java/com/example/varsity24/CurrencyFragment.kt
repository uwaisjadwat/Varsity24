package com.example.varsity24

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.varsity24.ApiClient.CurrencyApiClient
import com.example.varsity24.Models.CurrencyModel.Curency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CurrencyFragment : Fragment() {

    private lateinit var currencyValueText: TextView
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_currency, container, false)

        currencyValueText = rootView.findViewById(R.id.currency_value)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable = CompositeDisposable()
        fetchData()
    }

    private fun fetchData() {
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
    }

    private fun onResponse(response: Curency) {
        val newAmount = response.newAmount
        currencyValueText.text = "Amount= $newAmount"
        Toast.makeText(requireContext(), "$newAmount", Toast.LENGTH_SHORT).show()
        Log.i("[Success]", "$response")
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
        t.message?.let { Log.e("[Failed]", it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}