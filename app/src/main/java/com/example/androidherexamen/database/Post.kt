package com.example.androidherexamen.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidherexamen.R.drawable.image_placeholder

@Entity
data class Post(

    @PrimaryKey(autoGenerate = true)
    var postId: Long = 0L,

    var userId: Long = 0L,

    var text: String = "",

    var photo: String = "",

    var links: String = "",

    var favorite: Boolean = false

)