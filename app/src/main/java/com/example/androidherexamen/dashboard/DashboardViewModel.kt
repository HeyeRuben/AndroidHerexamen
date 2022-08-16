package com.example.androidherexamen.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class DashboardViewModel(val userId: Long, val database: PostDatabaseDAO, val dataSourceComments: CommentDatabaseDAO, application: Application) : AndroidViewModel(application){

    // Bevat de lijst met posts
    val posts = database.getAll() // Impl: via string formatter de data weergeven

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

    fun calculateBeantwoordePosts(){
        viewModelScope.launch {
            val commentsByBegeleider = dataSourceComments.getAllByUser(userId)

            posts.value?.map {
                it.postId == commentsByBegeleider?.postId
            }
        }
    }

    fun onBeantwoordePostsClicked(){
        calculateBeantwoordePosts()
    }

    fun onGelezenPostsClicked(){

    }

    fun onCommentsNavigated() {
        _navigateToComments.value = null
    }

}