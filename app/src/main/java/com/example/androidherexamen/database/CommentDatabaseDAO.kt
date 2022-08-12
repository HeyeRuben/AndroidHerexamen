package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommentDatabaseDAO {

    @Insert
    suspend fun insert (comment: Comment)

    @Update
    suspend fun update (comment: Comment)

    @Delete
    suspend fun delete (comment: Comment)

    @Query("SELECT * FROM Comment WHERE postId = :postId")
    fun getAllByPostId(postId: Long) : LiveData<List<Comment>>

}