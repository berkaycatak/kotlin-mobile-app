package com.h5190058.berkay_catak_kotlin.data

import AdsModelItem
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow

interface ListDataSource {
    fun getAllAds(): Flow<Resource<List<AdsModelItem>>>
}