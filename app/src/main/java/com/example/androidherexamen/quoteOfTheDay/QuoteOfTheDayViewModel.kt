package com.example.androidherexamen.quoteOfTheDay

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.repository.QuoteOfTheDayRepository
import kotlinx.coroutines.*

class QuoteOfTheDayViewModel(application: Application) : AndroidViewModel(application) {
    private val database = MyDatabase.getInstance(application.applicationContext)
    private val quoteOfTheDayRepository = QuoteOfTheDayRepository(database)

    val quoteOfTheDay = quoteOfTheDayRepository.quoteOfTheDayDatabase

    init {
        viewModelScope.launch {
            quoteOfTheDayRepository.refreshQuote()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}