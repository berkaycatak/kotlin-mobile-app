package com.h5190058.berkay_catak_kotlin.data.remote

import AdsModelItem
import UsersModelItem
import com.h5190058.berkay_catak_kotlin.data.ListDataSource
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import kotlin.Exception

class ListRetrofitDataSource : ListDataSource {
    override fun getAllAds(): Flow<Resource<List<AdsModelItem>>> = flow {

        try {
            emit(Resource.Loading())

            val response : Response<List<AdsModelItem>> = Service.build().getAllAds()
            if(response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        }catch (e: Exception){

        }
    }

}