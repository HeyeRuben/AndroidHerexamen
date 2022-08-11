package com.example.androidherexamen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidherexamen.R
import com.example.androidherexamen.database.Post

class PostAdapter : RecyclerView.Adapter<TextItemViewHolder>(){

    var data = listOf<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        // Alle velden die zichtbaar zijn worden hergebruikt dus in deze methode
        // Moet alles ingesteld worden
        val item = data[position]

        holder.textView.text = item.postId.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }



}