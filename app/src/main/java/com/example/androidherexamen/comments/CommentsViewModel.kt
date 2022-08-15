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

    val isResetButtonVisible = MutableLiveData(false)
    private var subCommentId : Long? = null
    private var isEdit: Boolean = false
    private var editCommentId: Long? = null

    // Bevat de lijst met posts
    val comments = database.getAllByPostId(postId) // Impl: via string formatter de data weergeven

    fun onSaveCommentClicked(){

        var isValidated = validateNewComment()

        if(isValidated){
            if(isEdit){
                // Edited comment
                saveEditedComment(editCommentId!!)
            } else {
                // New comment
                saveNewComment()
            }
        }

    }

    fun resetValues(){
        newCommentText.value = ""
        replyCommentNotifier.value = ""
        replyCommentNotifier.value = "Antwoord op post"
        isResetButtonVisible.value = false
        subCommentId = 0L
        isEdit = false
        editCommentId = null
    }

    fun onReplyToCommentClicked(commentId: Long){
        val comment = comments.value?.filter { it.commentId == commentId }
        replyCommentNotifier.value = "Antwoord op user: ${comment?.first()?.userId}"
        isResetButtonVisible.value = true
        subCommentId = commentId
    }

    private fun validateNewComment(): Boolean {
        return !newCommentText.value.isNullOrBlank()
    }

    private suspend fun insert(newComment: Comment) {
        database.insert(newComment)
    }

    private suspend fun update(comment: Comment){
        if(comment != null)
            database.update(comment)
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

    fun onEditCommentClicked(commentId: Long){
        editCommentId = commentId
        showEditCommentText(commentId)
        replyCommentNotifier.value = "Bewerk comment: $commentId"
        isEdit = true
        isResetButtonVisible.value = true
    }

    private fun showEditCommentText(commentId: Long){
        viewModelScope.launch {
            val comment = database.get(commentId)
            newCommentText.value = comment.text
        }
    }

    private fun saveEditedComment(commentId: Long){
        if(validateNewComment()){
            viewModelScope.launch {
                val comment = database.get(commentId)
                comment.text = newCommentText.value!!
                update(comment)
                resetValues()
            }
        }
    }

    private fun saveNewComment(){
        viewModelScope.launch {
            val newComment = Comment()
            newComment.postId = postId
            newComment.text = newCommentText.value.toString()
            newComment.isSubComment = isResetButtonVisible.value!!
            newComment.subCommentId = subCommentId
            insert(newComment)
            resetValues()
        }
    }
}