package com.example.androidherexamen.database

import java.util.*

data class Post (
    var postId: Long = 0L,

    var userId: Long = 0L,

    var text: String = "",

    var photo: String = "",

    var links: List<String>,

    var comments: List<Comment>,

    var date: Date

)