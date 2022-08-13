package com.example.androidherexamen.quoteOfTheDay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidherexamen.network.QuoteOfTheDayApi
import com.example.androidherexamen.network.QuoteOfTheDayProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteOfTheDayViewModel : ViewModel(){
    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getQuoteOfTheDayProperties() on init so we can display status immediately.
     */
    init {
        getQuoteOfTheDayProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getQuoteOfTheDayProperties() {
        QuoteOfTheDayApi.retrofitService.getProperties().enqueue( object: Callback<List<QuoteOfTheDayProperty>> {
            override fun onFailure(call: Call<List<QuoteOfTheDayProperty>>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<List<QuoteOfTheDayProperty>>, response: Response<List<QuoteOfTheDayProperty>>) {
                _response.value = response.body()?.get(0)?.q
            }
        })
    }
}