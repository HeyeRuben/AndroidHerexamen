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
    val viewId = MutableLiveData(1)

    private val liveDataMerger = MediatorLiveData<Pair<String, Int>>().apply {
        addSource(userId) { value = Pair(it!!, viewId.value!!) }
        addSource(viewId) { value = Pair(userId.value!!, it!!) }
    }

    // Bevat de lijst met posts
    val posts: LiveData<List<Post>> =
        Transformations.switchMap(liveDataMerger) { getPostsFromDB(liveDataMerger) }

    private val _navigateToComments = MutableLiveData<Long?>()
    val navigateToComments
        get() = _navigateToComments


    private fun getPostsFromDB(params: MediatorLiveData<Pair<String, Int>>): LiveData<List<Post>>{

        val userId = params.value?.first.toString()
        val typeId = params.value?.second


        when (typeId) {
            // 1 -> Nieuw
            1 -> return database.getAllNieuwePosts()
            // 2 -> Beantwoord
            2 -> return database.getAllPostsWithCommentsByUser(userId)
            // 3 -> Gelezen
            3 -> return database.getAllGelezenPosts()
        }

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