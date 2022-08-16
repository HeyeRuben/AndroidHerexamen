package com.example.androidherexamen.network

import com.example.androidherexamen.database.DatabaseQuoteOfTheDay
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkQuoteOfTheDayContainer(val qotd: List<QuoteOfTheDayProperty>)

fun NetworkQuoteOfTheDayContainer.asDatabaseModel(): Array<DatabaseQuoteOfTheDay> {
    return qotd.map {
        DatabaseQuoteOfTheDay (
            quote = it.q,
            author = it.a,
            date = it.date)
    }.toTypedArray()
}