package com.example.androidherexamen.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidherexamen.database.PostDatabaseDAO

class ProfileViewModelFactory(
    private val userId: Int,
    private val dataSource: PostDatabaseDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}