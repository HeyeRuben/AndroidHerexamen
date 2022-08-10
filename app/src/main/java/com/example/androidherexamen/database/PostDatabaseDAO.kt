package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostDatabaseDAO {

    @Insert
    suspend fun insert (post: Post)

    @Update
    suspend fun update (post: Post)

    /*
    @Delete
    fun delete (post: Post)
    */

    @Query("SELECT * FROM Post_table")
    fun getAll() : LiveData<List<Post>>

}