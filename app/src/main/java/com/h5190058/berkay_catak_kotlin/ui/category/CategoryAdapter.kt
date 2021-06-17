package com.h5190058.berkay_catak_kotlin.ui.category

import CategoriesModelItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190058.berkay_catak_kotlin.R

class CategoryAdapter(val categoryList: ArrayList<CategoriesModelItem>, val listener: ItemClickListener) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_item, parent, false)
        return CategoryViewHolder(v)
    }

    companion object {
        var mClickListener: ItemClickListener? = null
    }

    interface ItemClickListener
    {
        fun onIemClick(position: Int)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindItems(categoryList[position])
        mClickListener = listener
        holder.itemView.setOnClickListener { view ->
            mClickListener?.onIemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
