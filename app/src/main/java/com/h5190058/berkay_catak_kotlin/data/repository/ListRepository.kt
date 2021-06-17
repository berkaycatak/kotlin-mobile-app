package com.h5190058.berkay_catak_kotlin.data.repository

import AdsModelItem
import UsersModelItem
import com.h5190058.berkay_catak_kotlin.data.ListDataSource
import com.h5190058.berkay_catak_kotlin.data.UsersDataSource
import com.h5190058.berkay_catak_kotlin.data.remote.ListRetrofitDataSource
import com.h5190058.berkay_catak_kotlin.data.remote.UsersRetrofitDataSource
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow

class ListRepository {
    private var listDataSource: ListDataSource?=null

    init {
        listDataSource = ListRetrofitDataSource()
    }

    fun getAllAds(): Flow<Resource<List<AdsModelItem>>>
    {
        return listDataSource!!.getAllAds()
    }
}