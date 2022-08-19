package com.example.androidherexamen.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PostDatabaseDAO {

    @Insert
    suspend fun insert(post: Post)

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)

    @Query("SELECT * FROM Post WHERE gelezen == 0")
    fun getAllNieuwePosts(): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE gelezen == 1")
    fun getAllGelezenPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE userId == :id ORDER BY postId DESC")
    fun getAll(id: String): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE favorite == 1 AND userId == :id ORDER BY postId DESC")
    fun getAllFavPosts(id: String): LiveData<List<Post>>

    @Query("SELECT * FROM Post WHERE postId = :id")
    suspend fun get(id: Long): Post

    @Query("SELECT p.postId, p.userId, p.text, p.photo, p.links, p.favorite, p.username, p.gelezen FROM Post p  JOIN Comment c ON p.postId == c.postId WHERE c.userId == :id")
    fun getAllPostsWithCommentsByUser(id: String): LiveData<List<Post>>
}