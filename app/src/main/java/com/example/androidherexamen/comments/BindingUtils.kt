package com.example.androidherexamen.comments

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.androidherexamen.database.Comment

@BindingAdapter("commentText")
fun TextView.setCommentText(item: Comment?) {
    item?.let {
        text = item.text
    }
}

@BindingAdapter("commentHeader")
fun TextView.setHeaderext(item: Comment?) {
    item?.let {

        if (item.isSubComment) {
            "${item.username} > ${item.subCommentUsername}".also { text = it }
        } else {
            "${item.username}".also { text = it }
        }
    }
}