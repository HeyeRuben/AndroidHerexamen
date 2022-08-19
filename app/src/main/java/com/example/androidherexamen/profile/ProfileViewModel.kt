package com.example.androidherexamen.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class ProfileViewModel(val database: PostDatabaseDAO, application: Application) : AndroidViewModel(application) {

    val userId = MutableLiveData("")
    val userName = MutableLiveData("")

    // Bevat de lijst met posts
    val posts: LiveData<List<Post>> =
        Transformations.switchMap(userId) { getPostsFromDB(it) }

    private fun getPostsFromDB(userId: String): LiveData<List<Post>> {
        return database.getAllFavPosts(userId)
    }

    private suspend fun delete(post: Post) {
        if (post != null)
            database.delete(post)
    }

    private suspend fun update(post: Post) {
        if (post != null)
            database.update(post)
    }

    fun onGelezenPostClicked(postId: Long) {
        viewModelScope.launch {
            val post = database.get(postId)

            post.gelezen = post.gelezen != true

            update(post)
        }
    }

    private val _navigateToComments = MutableLiveData<Long?>()
    val navigateToComments
        get() = _navigateToComments

    fun onCommentsClicked(postId: Long) {
        _navigateToComments.value = postId
    }

    fun onDeletePostClicked(postId: Long) {
        viewModelScope.launch {
            val post = database.get(postId)
            delete(post)
        }
    }

    fun onFavoritePostClicked(postId: Long) {
        viewModelScope.launch {
            val post = database.get(postId)

            post.favorite = post.favorite != true

            update(post)
        }
    }

    fun onCommentsNavigated() {
        _navigateToComments.value = null
    }
}