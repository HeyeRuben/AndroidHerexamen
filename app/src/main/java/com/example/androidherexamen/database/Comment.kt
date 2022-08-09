package com.example.androidherexamen.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Comment (

    @PrimaryKey(autoGenerate = true)
    var commentId: Long = 0L,

    var userId: Long = 0L,

    var postId: Long = 0L,

    var text: String = "",

    var isSubComment: Boolean = false,

    var subCommentId: Long = 0L,

    var date: Date

)