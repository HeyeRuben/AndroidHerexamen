package com.example.androidherexamen.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class ProfileViewModel(userId: Int, val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    // Bevat de lijst met favoriete posts
    val favPosts = database.getAll() // Impl: via string formatter de data weergeven

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