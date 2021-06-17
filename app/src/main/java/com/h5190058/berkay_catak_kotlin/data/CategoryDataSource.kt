package com.h5190058.berkay_catak_kotlin.data

import CategoriesModelItem
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesDataSource {
    fun getAllCategories(): Flow<Resource<List<CategoriesModelItem>>>
}