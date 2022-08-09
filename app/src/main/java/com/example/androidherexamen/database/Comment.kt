package com.example.androidherexamen.database

data class Comment (
    var commentId: Long = 0L,

    var userId: Long = 0L,

    var postId: Long = 0L,

    var text: String = "",

    var isSubComment: Boolean = false,

    var subCommentId: Long = 0L

)