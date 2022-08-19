package com.example.androidherexamen.main

import android.content.SharedPreferences
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
        setImageBitmap(it.photo)
    }
}

@BindingAdapter("postLinks")
fun TextView.setPostLinks(item: Post?) {
    item?.let {
        text = item.links
    }
}

@BindingAdapter("postUsername")
fun TextView.setPostUsername(item: Post?) {
    item?.let {
        text = item.username
    }
}

@BindingAdapter("postFavorite")
fun CheckBox.setPostFavorite(item: Post?) {
    item?.let {
        isChecked = item.favorite
    }
}

@BindingAdapter("postGelezen")
fun CheckBox.setPostGelezen(item: Post?) {
    item?.let {
        isChecked = item.gelezen
    }
}