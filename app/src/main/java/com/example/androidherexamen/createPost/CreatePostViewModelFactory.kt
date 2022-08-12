package com.example.androidherexamen.createPost

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.PostDatabaseDAO

class CreatePostViewModelFactory(private val dataSource: PostDatabaseDAO,
                                 private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreatePostViewModel::class.java)) {
            return CreatePostViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}