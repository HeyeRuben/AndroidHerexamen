package com.example.androidherexamen.main

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidherexamen.Roles
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.databinding.PostViewBinding

class PostAdapter(
    val clickListener: PostCommentsListener,
    val deletePostListener: DeletePostListener,
    val addPostToFavoritesListener: AddPostToFavoritesClickListener,
    val addPostToGelezenListener: AddPostToGelezenClickListener
) : ListAdapter<Post, PostAdapter.ViewHolder>(
    PostDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener, deletePostListener, addPostToFavoritesListener, addPostToGelezenListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PostViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Post,
            clickListener: PostCommentsListener,
            deleteClickListener: DeletePostListener,
            addPostToFavoritesListener: AddPostToFavoritesClickListener,
            addPostToGelezenClickListener: AddPostToGelezenClickListener
        ) {

            binding.post = item
            binding.commentsClickListener = clickListener
            binding.deletePostClickListener = deleteClickListener
            binding.addPostToFavoritesClickListener = addPostToFavoritesListener
            binding.addPostToGelezenClickListener = addPostToGelezenClickListener

            val sp: SharedPreferences = itemView.context.getSharedPreferences("LoggedInUser",
                Context.MODE_PRIVATE
            )

            if (sp.getString("role", null) == Roles.BEGELEIDER.toString()){
                binding.postFavorite.visibility = View.INVISIBLE
                binding.postGelezen.visibility = View.VISIBLE
                binding.postDelete.visibility = View.INVISIBLE
            } else {
                binding.postFavorite.visibility = View.VISIBLE
                binding.postGelezen.visibility = View.INVISIBLE
                binding.postDelete.visibility = View.VISIBLE
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}

class PostCommentsListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(post: Post) = clickListener(post.postId)
}

class DeletePostListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(post: Post) = clickListener(post.postId)
}

class AddPostToFavoritesClickListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(post: Post) = clickListener(post.postId)
}

class AddPostToGelezenClickListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(post: Post) = clickListener(post.postId)
}