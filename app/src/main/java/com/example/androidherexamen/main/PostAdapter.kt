package com.example.androidherexamen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.databinding.PostViewBinding

class PostAdapter(val clickListener: PostCommentsListener) : ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PostViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Post, clickListener: PostCommentsListener) {

            binding.post = item
            binding.commentsClickListener = clickListener
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

class PostDiffCallback : DiffUtil.ItemCallback<Post>(){

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}

class PostCommentsListener(val clickListener: (postId: Long) -> Unit){
    fun onClick(post: Post) = clickListener(post.postId)
}