package com.example.androidherexamen.dashboard

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class DashboardViewModel(
    val database: PostDatabaseDAO,
    application: Application
) : AndroidViewModel(application) {

    val userId = MutableLiveData("")

    // Bevat de lijst met posts
    val posts: LiveData<List<Post>> =
        Transformations.switchMap(userId) { getPostsFromDB(it) }

    private val _navigateToComments = MutableLiveData<Long?>()
    val navigateToComments
        get() = _navigateToComments


    private fun getPostsFromDB(userId: String): LiveData<List<Post>>{
        return database.getAllPostsWithCommentsByUser(userId)
    }

    fun onCommentsClicked(postId: Long) {
        _navigateToComments.value = postId
    }

    fun onDeletePostClicked(postId: Long) {
        // mag dit niet doen
    }

    fun onFavoritePostClicked(postId: Long) {
        // mag dit niet doen
    }

    fun onNieuwePostsClicked() {
    }

    fun onGelezenPostsClicked() {
    }

    fun onBeantwoordePostsClicked() {
    }

    fun onGelezenPostClicked(postId: Long) {
        viewModelScope.launch {
            val post = database.get(postId)

            post.gelezen = post.gelezen != true

            update(post)
        }
    }

    private suspend fun update(post: Post) {
        if (post != null)
            database.update(post)
    }

    fun onCommentsNavigated() {
        _navigateToComments.value = null
    }
}