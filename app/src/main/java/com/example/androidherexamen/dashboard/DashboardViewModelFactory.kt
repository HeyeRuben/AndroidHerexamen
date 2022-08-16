package com.example.androidherexamen.dashboard

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidherexamen.database.PostDatabaseDAO

class DashboardViewModelFactory(
    private val userId: Long,
    private val dataSource: PostDatabaseDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(userId, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}