package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDatabaseDAO {

    @Insert
    fun insert (post: Post)

    @Update
    fun update (post: Post)

    @Delete
    fun delete (post: Post)

    @Query("SELECT * FROM Post WHERE userId = :userId ORDER BY date ASC")
    fun getAllByUserId(userId: Long) : LiveData<List<Post>>

}