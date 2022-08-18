package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuoteOfTheDayDAO {
    @Query("SELECT * FROM quote_table ORDER BY quoteId DESC")
    fun getQuote(): LiveData<DatabaseQuoteOfTheDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuoteToRoom(quote: DatabaseQuoteOfTheDay)
}