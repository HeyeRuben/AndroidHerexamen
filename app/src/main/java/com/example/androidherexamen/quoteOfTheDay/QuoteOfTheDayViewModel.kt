package com.example.androidherexamen.quoteOfTheDay

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.network.QuoteOfTheDayApi
import com.example.androidherexamen.network.QuoteOfTheDayProperty
import com.example.androidherexamen.repository.QuoteOfTheDayRepository
import kotlinx.coroutines.*
import java.lang.Exception

class QuoteOfTheDayViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MyDatabase.getInstance(application.applicationContext)
    private val quoteOfTheDayRepository = QuoteOfTheDayRepository(database)

    val quoteOfTheDay = quoteOfTheDayRepository.quoteOfTheDayDatabase

    init {
        viewModelScope.launch {
            quoteOfTheDayRepository.refreshQuote()
        }
    }

    /*
    private suspend fun getQuoteOfTheDayProperties() {

        var getQOTDDeferred = QuoteOfTheDayApi.retrofitService.getPropertiesAsync()
        try {
            var res = getQOTDDeferred.await()
            _response.value = res
        } catch (e: Exception) {
            println("failed: " + e.toString())
        }

    }
    */

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    /**
     * Factory for constructing FromAPIViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuoteOfTheDayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuoteOfTheDayViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

