package com.h5190058.berkay_catak_kotlin.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.h5190058.berkay_catak_kotlin.R

fun ImageView.getImage(imageUrl: String){
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.drawable.pp)
        .centerCrop()
        .into(this);
}