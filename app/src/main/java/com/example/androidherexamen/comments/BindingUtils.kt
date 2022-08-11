package com.example.androidherexamen.comments

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.androidherexamen.database.Comment
import com.example.androidherexamen.database.Post

@BindingAdapter("commentText")
fun TextView.setCommentText(item: Comment?) {
    item?.let {
        text = item.text
    }
}

// Nog toe te voegen voor date & links