package com.example.androidherexamen.main

import android.app.Application
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidherexamen.R
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class MainViewModel(userId: Int, val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    // Bevat de lijst met posts
    val posts = database.getAll() // Impl: via string formatter de data weergeven

    private suspend fun delete(post: Post){
        if(post != null)
        database.delete(post)
    }

    private val _navigateToComments = MutableLiveData<Long?>()
    val navigateToComments
        get() = _navigateToComments

    fun onCommentsClicked(postId: Long){
        _navigateToComments.value = postId
    }

    fun onDeletePostClicked(postId: Long){
        viewModelScope.launch {
            val post = database.get(postId)
            delete(post)
        }
    }

    fun onCommentsNavigated() {
        _navigateToComments.value = null
    }
}