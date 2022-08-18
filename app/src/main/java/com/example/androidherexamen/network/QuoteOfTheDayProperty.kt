package com.example.androidherexamen.network

import com.example.androidherexamen.database.DatabaseQuoteOfTheDay

data class QuoteOfTheDayProperty(

    val q: String,
    val a: String
)

fun QuoteOfTheDayProperty.asDBModel(): DatabaseQuoteOfTheDay {
    return DatabaseQuoteOfTheDay(quote = q, author = a)
}
