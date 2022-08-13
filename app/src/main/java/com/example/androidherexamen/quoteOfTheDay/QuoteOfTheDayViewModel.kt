package com.example.androidherexamen.quoteOfTheDay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidherexamen.network.QuoteOfTheDayApi
import com.example.androidherexamen.network.QuoteOfTheDayApiService
import com.example.androidherexamen.network.QuoteOfTheDayProperty
import kotlinx.coroutines.*
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

        viewModelScope.launch {
                try{

                    var listResult = QuoteOfTheDayApi.retrofitService.getProperties()
                    _response.value = listResult[0].q

                } catch(t:Throwable) {

                    _response.value = "Failure: " + t.message

                }

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}