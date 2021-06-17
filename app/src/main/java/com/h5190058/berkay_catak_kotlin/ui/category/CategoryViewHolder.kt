package com.h5190058.berkay_catak_kotlin.ui.category

import CategoriesModelItem
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.util.getImage
import java.lang.Exception

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItems(category: CategoriesModelItem) {
        val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView
        val categoryImage  = itemView.findViewById(R.id.categoryImage) as ImageView
        textViewName.text = category.name
        categoryImage.getImage(category.image)
    }
}