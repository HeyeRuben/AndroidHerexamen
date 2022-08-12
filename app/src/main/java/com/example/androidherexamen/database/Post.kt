package com.example.androidherexamen.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidherexamen.R.drawable.image_holder

@Entity
data class Post(

    @PrimaryKey(autoGenerate = true)
    var postId: Long = 0L,

    var userId: Long = 0L,

    var text: String = "",

    var photo: Int = image_holder,

    //var links: MutableList<String>

    //var comments: List<Comment>,

    //var date: Date

)