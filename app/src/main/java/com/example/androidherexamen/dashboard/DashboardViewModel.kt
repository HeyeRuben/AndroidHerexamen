package com.example.androidherexamen.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class DashboardViewModel(val userId: Long, val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application){

    // Bevat de lijst met posts
    val posts = database.getAllPostsWithCommentsByUser(userId)


    private val _navigateToComments = MutableLiveData<Long?>()
    val navigateToComments
        get() = _navigateToComments

    fun onCommentsClicked(postId: Long){
        _navigateToComments.value = postId
    }

    fun onDeletePostClicked(postId: Long){
        // mag dit niet doen
    }

    fun onFavoritePostClicked(postId: Long){
        // mag dit niet doen
    }

    fun onNieuwePostsClicked(){

    }

    fun onBeantwoordePostsClicked(){

    }

    fun onGelezenPostsClicked(){

    }

    fun onCommentsNavigated() {
        _navigateToComments.value = null
    }

}