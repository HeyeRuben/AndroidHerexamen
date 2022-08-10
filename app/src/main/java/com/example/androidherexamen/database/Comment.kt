package com.example.androidherexamen.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "Comment_table")
data class Comment(

    @PrimaryKey(autoGenerate = true)
    var commentId: Long = 0L,

    var userId: Long = 0L,

    var postId: Long = 0L,

    var text: String = "",

    var isSubComment: Boolean = false,

    var subCommentId: Long = 0L,

    // var date: LocalDateTime = LocalDateTime.now()

)