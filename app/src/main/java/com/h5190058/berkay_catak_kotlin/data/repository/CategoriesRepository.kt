package com.h5190058.berkay_catak_kotlin.data.repository

import CategoriesModelItem
import com.h5190058.berkay_catak_kotlin.data.CategoriesDataSource
import com.h5190058.berkay_catak_kotlin.data.remote.CategoryRetrofitDataSource
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow

class CategoriesRepository {
    private var categoriesDataSource: CategoriesDataSource?=null

    init {
        categoriesDataSource = CategoryRetrofitDataSource()
    }

    fun getAllCategories(): Flow<Resource<List<CategoriesModelItem>>>
    {
        return categoriesDataSource!!.getAllCategories()
    }
}