package com.example.androidherexamen.comments

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class CommentsViewModel(val postId: Long, val database: CommentDatabaseDAO, application: Application) : AndroidViewModel(application){

    // Bevat de lijst met posts
    val comments = database.getAllByPostId(postId) // Impl: via string formatter de data weergeven

    fun updateList(){
        viewModelScope.launch{
            val newComment = Comment()
            newComment.postId = postId
            newComment.text = "Random comment (als nummer lol): " + kotlin.random.Random.nextInt().toString()
            insert(newComment)
        }
    }

    private suspend fun insert(newComment: Comment) {
        database.insert(newComment)
    }

    private suspend fun delete(comment: Comment) {
        if(comment != null)
        database.delete(comment)
    }

    fun onDeleteCommentClicked(commentId: Long){
        viewModelScope.launch {
            val comment = database.get(commentId)
            delete(comment)
        }
    }
}