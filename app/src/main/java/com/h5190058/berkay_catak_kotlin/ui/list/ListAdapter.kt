package com.h5190058.berkay_catak_kotlin.ui.list

import AdsModelItem
import CategoriesModelItem
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190058.berkay_catak_kotlin.R
import com.h5190058.berkay_catak_kotlin.ui.category.CategoryAdapter

class ListAdapter(val adsList: ArrayList<AdsModelItem>, val listener: ItemClickListener) : RecyclerView.Adapter<ListViewHolder>() {

    companion object {
        var mClickListener: ItemClickListener? = null
    }

    interface ItemClickListener
    {
        fun onIemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindItems(adsList[position])

        mClickListener = listener
        holder.itemView.setOnClickListener { view ->
            mClickListener?.onIemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return adsList.size
    }

}
