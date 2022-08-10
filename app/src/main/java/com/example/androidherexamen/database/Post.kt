package com.example.androidherexamen.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "Post_table")
data class Post(

    @PrimaryKey(autoGenerate = true)
    var postId: Long = 0L,

    var userId: Long = 1L,

    var text: String = "test",

    var photo: String = "www.test.be",

   // var links: List<String> = listOf(),

   // var comments: List<Comment> = listOf(),

    // var date: LocalDateTime = LocalDateTime.now()

)