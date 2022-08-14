package com.example.androidherexamen.comments

import android.app.Application
import androidx.lifecycle.*
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.CommentDatabaseDAO
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.database.PostDatabaseDAO
import kotlinx.coroutines.launch

class CommentsViewModel(val postId: Long, val database: CommentDatabaseDAO, application: Application) : AndroidViewModel(application){

    val newCommentText = MutableLiveData("")
    val replyCommentNotifier = MutableLiveData("Antwoord op post")

    val isSubComment = MutableLiveData(false)
    private var subCommentId : Long? = null

    // Bevat de lijst met posts
    val comments = database.getAllByPostId(postId) // Impl: via string formatter de data weergeven

    fun onSaveCommentClicked(){

        var isValidated = validateNewComment()

        if(isValidated){

            viewModelScope.launch {
                val newComment = Comment()
                newComment.postId = postId
                newComment.text = newCommentText.value.toString()
                newComment.isSubComment = isSubComment.value!!
                newComment.subCommentId = subCommentId
                insert(newComment)
                resetValues()
            }
        }

    }

    fun resetValues(){
        newCommentText.value = ""
        replyCommentNotifier.value = ""
        replyCommentNotifier.value = "Antwoord op post"
        isSubComment.value = false
        subCommentId = 0L
    }

    fun onReplyToCommentClicked(commentId: Long){
        replyCommentNotifier.value = "Antwoord op: $commentId"
        isSubComment.value = true
        subCommentId = commentId
    }

    private fun validateNewComment(): Boolean {
        return !newCommentText.value.isNullOrBlank()
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