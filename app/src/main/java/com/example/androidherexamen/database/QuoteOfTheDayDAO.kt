package com.example.androidherexamen.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidherexamen.network.QuoteOfTheDayProperty

@Dao
interface QuoteOfTheDayDAO{

    @Query("SELECT * FROM databasequoteoftheday")
    fun getQuoteOfTheDay(): List<QuoteOfTheDayProperty>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quoteOfTheDayProperty: QuoteOfTheDayProperty)

}