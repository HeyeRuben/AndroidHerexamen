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

fun List<DatabaseQuoteOfTheDay>.asDomainModel(): List<QuoteOfTheDayProperty> {
    return map {
        QuoteOfTheDayProperty (
            q = it.quote,
            a = it.author,
            date = it.date)
    }
}