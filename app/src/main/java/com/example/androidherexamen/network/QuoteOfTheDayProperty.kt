package com.example.androidherexamen.network

import com.example.androidherexamen.database.DatabaseQuoteOfTheDay
import com.squareup.moshi.Json

data class QuoteOfTheDayProperty(

    @Json(name="content")
    val q: String,

    @Json(name="author")
    val a: String
)

fun QuoteOfTheDayProperty.asDBModel(): DatabaseQuoteOfTheDay {
    return DatabaseQuoteOfTheDay(quote = q, author = a)
}
