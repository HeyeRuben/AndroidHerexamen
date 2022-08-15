package com.example.androidherexamen.main

import android.widget.CheckBox
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

@BindingAdapter("postFavorite")
fun CheckBox.setPostFavorite(item: Post?) {
    item?.let {
        isChecked = item.favorite
    }
}

// Nog toe te voegen voor date & links