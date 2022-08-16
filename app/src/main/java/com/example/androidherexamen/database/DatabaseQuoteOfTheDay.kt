package com.example.androidherexamen.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidherexamen.network.QuoteOfTheDayProperty

@Entity
data class DatabaseQuoteOfTheDay (

    @PrimaryKey
    val date: String,
    val quote: String,
    val author: String

)