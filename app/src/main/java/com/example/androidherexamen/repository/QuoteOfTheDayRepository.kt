package com.example.androidherexamen.repository

import androidx.lifecycle.LiveData
import com.example.androidherexamen.database.DatabaseQuoteOfTheDay
import com.example.androidherexamen.database.MyDatabase
import com.example.androidherexamen.network.QuoteOfTheDayApi
import com.example.androidherexamen.network.asDBModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteOfTheDayRepository(private val database: MyDatabase) {

    // Database call
    val quoteOfTheDayDatabase: LiveData<DatabaseQuoteOfTheDay> = database.quoteOfTheDayDAO.getQuote()

    // Network call
    suspend fun refreshQuote() {
        withContext(Dispatchers.IO) {
            val quoteOfTheDayNetwork = QuoteOfTheDayApi.retrofitService.getPropertiesAsync().await()
            database.quoteOfTheDayDAO.insertQuoteToRoom(quoteOfTheDayNetwork.asDBModel())
        }
    }



}