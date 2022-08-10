package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommentDatabaseDAO {

    @Insert
    fun insert (comment: Comment)

    @Update
    fun update (comment: Comment)

    @Delete
    fun delete (comment: Comment)

    @Query("SELECT * FROM Comment WHERE postId = :postId")
    fun getAllByPostId(postId: Long) : LiveData<List<Comment>>

}