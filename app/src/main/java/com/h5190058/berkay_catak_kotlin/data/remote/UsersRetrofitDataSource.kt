package com.h5190058.berkay_catak_kotlin.data.remote

import UsersModelItem
import com.h5190058.berkay_catak_kotlin.data.UsersDataSource
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import kotlin.Exception

class UsersRetrofitDataSource : UsersDataSource {
    override fun getAllUsers(): Flow<Resource<List<UsersModelItem>>> = flow {

        try {
            emit(Resource.Loading())

            val response : Response<List<UsersModelItem>> = Service.build().getAllUsers()
            if(response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        }catch (e: Exception){

        }
    }

}