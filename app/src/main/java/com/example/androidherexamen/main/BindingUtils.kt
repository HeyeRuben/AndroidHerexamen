package com.example.androidherexamen.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
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

@BindingAdapter("postFavorite")
fun CheckBox.setPostFavorite(item: Post?) {
    item?.let {
        isChecked = item.favorite
    }
}

fun convertStringToBitmap(string: String?): Bitmap? {
    val byteArray1: ByteArray = Base64.decode(string, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(
        byteArray1, 0,
        byteArray1.size
    )
}