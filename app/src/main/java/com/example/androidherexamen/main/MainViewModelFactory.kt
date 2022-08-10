package com.example.androidherexamen.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidherexamen.database.PostDatabaseDAO

class MainViewModelFactory(private val userId: Int, private val dataSource: PostDatabaseDAO,
                           private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}