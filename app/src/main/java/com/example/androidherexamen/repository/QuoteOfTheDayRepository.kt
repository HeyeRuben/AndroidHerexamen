package com.example.androidherexamen.repository

import com.example.androidherexamen.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteOfTheDayRepository(private val database: MyDatabase){

    suspend fun refreshQuoteOfTheDay(){
        withContext(Dispatchers.IO) {

        }
    }

}