package com.example.androidherexamen.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="quote_table")
data class DatabaseQuoteOfTheDay (

    @PrimaryKey(autoGenerate = true)
    var quoteId: Long = 0L,
    var quote: String = "",
    var author: String = ""

)