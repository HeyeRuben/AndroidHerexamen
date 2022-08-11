package com.example.androidherexamen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidherexamen.database.Post
import com.example.androidherexamen.databinding.PostViewBinding

class PostAdapter : ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PostViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Post) {

            // Hier alle gegevens uit item halen bv item. momenteel nog hardcoded voor testing
            binding.postDate.text = "11/08/2022"
            binding.postText.text = item.text
            //binding.postImage //nog op te zoeken
            binding.postLinks.text = "Hier kunnen links verschijnen"
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