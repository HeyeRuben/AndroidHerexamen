package com.example.androidherexamen.quoteOfTheDay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidherexamen.network.QuoteOfTheDayApi
import com.example.androidherexamen.network.QuoteOfTheDayProperty
import kotlinx.coroutines.*
import java.lang.Exception

class QuoteOfTheDayViewModel : ViewModel() {
    // The internal MutableLiveData Property that stores the most recent response
    private val _response = MutableLiveData<QuoteOfTheDayProperty>()

    // The external immutable LiveData for the response Property
    val response: LiveData<QuoteOfTheDayProperty>
        get() = _response


    init {
        viewModelScope.launch {
            getQuoteOfTheDayProperties()
        }
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private suspend fun getQuoteOfTheDayProperties() {

        var getQOTDDeferred = QuoteOfTheDayApi.retrofitService.getPropertiesAsync()
        try {
            var res = getQOTDDeferred.await()
            _response.value = res
        } catch (e: Exception) {
            println("failed: " + e.toString())
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}