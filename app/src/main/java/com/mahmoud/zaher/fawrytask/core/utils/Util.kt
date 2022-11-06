package com.mahmoud.zaher.fawrytask.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mahmoud.zaher.fawrytask.R


fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}

fun ImageView.loadImage(uri: String) {
    val options = RequestOptions()
        .placeholder(getProgressDrawable(context))
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade()) //fading animation
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun imageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        imageView.loadImage(url)
    }
}
