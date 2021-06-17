package com.h5190058.berkay_catak_kotlin.data.remote

import CategoriesModelItem
import com.h5190058.berkay_catak_kotlin.data.CategoriesDataSource
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Exception

class CategoryRetrofitDataSource : CategoriesDataSource {
    override fun getAllCategories(): Flow<Resource<List<CategoriesModelItem>>> = flow {
       try {
           emit(Resource.Loading())

           val response = Service.build().getAllCategories()
           if(response.isSuccessful){
               response.body()?.let {
                   emit(Resource.Success(it))
               }
           }

       }catch (e: Exception){

       }
    }
}