package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDatabaseDAO {

    @Insert
    suspend fun insert(post: Post)

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)

    @Query("SELECT * FROM Post ORDER BY postId DESC")
    fun getAll(): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE favorite == 1 ORDER BY postId DESC")
    fun getAllFavPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE postId = :id")
    suspend fun get(id: Long): Post

    @Query("SELECT p.postId, p.userId, p.text, p.photo, p.links, p.favorite, p.username FROM Post p  JOIN Comment c ON p.postId == c.postId WHERE c.userId == :id")
    fun getAllPostsWithCommentsByUser(id: Long): LiveData<List<Post>>
}