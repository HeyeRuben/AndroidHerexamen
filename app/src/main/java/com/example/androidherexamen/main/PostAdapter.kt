package com.example.androidherexamen.main

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidherexamen.R
import com.example.androidherexamen.database.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    var data = listOf<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Alle velden die zichtbaar zijn worden hergebruikt dus in deze methode
        // Moet alles ingesteld worden
        val item = data[position]
        holder.bind(item)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.post_image)
        val postText: TextView = itemView.findViewById(R.id.post_text)
        val date: TextView = itemView.findViewById(R.id.post_date)
        val links: TextView = itemView.findViewById(R.id.post_links)

        fun bind(item: Post) {

            // Hier alle gegevens uit item halen bv item. momenteel nog hardcoded voor testing
            date.text = "11/08/2022"
            postText.text = item.text
            // holder.image nog op te zoeken
            links.text = "Hier kunnen links verschijnen"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.post_view, parent, false)
                return ViewHolder(view)
            }
        }

    }
}