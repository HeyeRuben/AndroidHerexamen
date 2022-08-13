package com.example.androidherexamen.main

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.androidherexamen.database.Post

@BindingAdapter("postText")
fun TextView.setPostText(item: Post?) {
    item?.let {
        text = item.text
    }
}

@BindingAdapter("postImage")
fun ImageView.setPostImage(item: Post?) {
    item?.let {
        setImageResource(item.photo)
    }
}

@BindingAdapter("postLinks")
fun TextView.setPostLinks(item: Post?) {
    item?.let {
        text = item.links
    }
}

// Nog toe te voegen voor date & links