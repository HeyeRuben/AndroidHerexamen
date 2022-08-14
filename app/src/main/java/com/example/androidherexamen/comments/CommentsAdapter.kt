package com.example.androidherexamen.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.databinding.CommentsViewBinding
import com.example.androidherexamen.databinding.PostViewBinding
import com.example.androidherexamen.main.DeletePostListener

class CommentsAdapter(val deleteCommentListener: DeleteCommentListener, val replyCommentListener: ReplyCommentListener) : ListAdapter<Comment, CommentsAdapter.ViewHolder>(
    CommentDiffCallback()
){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), deleteCommentListener, replyCommentListener)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: CommentsViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Comment, deleteCommentListener: DeleteCommentListener, replyCommentListener: ReplyCommentListener) {

            binding.comment = item
            binding.deleteCommentClickListener = deleteCommentListener
            binding.replyCommentClickListener = replyCommentListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentsViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CommentDiffCallback : DiffUtil.ItemCallback<Comment>(){

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.commentId == newItem.commentId
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }

}

class DeleteCommentListener(val clickListener: (commentId: Long) -> Unit){
    fun onClick(comment: Comment) = clickListener(comment.commentId)
}

class ReplyCommentListener(val clickListener: (commentId: Long) -> Unit){
    fun onClick(comment: Comment) = clickListener(comment.commentId)
}