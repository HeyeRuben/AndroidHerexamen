package com.example.androidherexamen.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Post (

    @PrimaryKey(autoGenerate = true)
    var postId: Long = 0L,

    var userId: Long = 0L,

    var text: String = "",

    var photo: String = "",

    //var links: List<String>,

    //var comments: List<Comment>,

    //var date: Date

)