package com.example.androidherexamen.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidherexamen.database.CommentDatabaseDAO

class CommentsViewModelFactory(
    private val postId: Long,
    private val dataSource: CommentDatabaseDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(postId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}