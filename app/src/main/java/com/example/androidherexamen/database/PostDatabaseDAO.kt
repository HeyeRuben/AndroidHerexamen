package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDatabaseDAO {

    @Insert
    suspend fun insert (post: Post)

    @Update
    suspend fun update (post: Post)

    @Delete
    suspend fun delete (post: Post)

    @Query("SELECT * FROM Post ORDER BY postId DESC")
    fun getAll() : LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE favorite == 0 ORDER BY postId DESC")
    fun getAllFavPosts() : LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE postId = :id")
    suspend fun get(id: Long) : Post

}