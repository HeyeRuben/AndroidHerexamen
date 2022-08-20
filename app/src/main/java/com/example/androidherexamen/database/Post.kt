package com.example.androidherexamen.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(

    @PrimaryKey(autoGenerate = true)
    var postId: Long = 0L,

    var userId: String = "",

    var text: String = "",

    var photo: Bitmap? = null,

    var links: String = "",

    var favorite: Boolean = false,

    var gelezen: Boolean = false,

    var username: String = ""

)