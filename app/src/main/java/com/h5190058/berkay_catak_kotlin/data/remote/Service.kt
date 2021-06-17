package com.h5190058.berkay_catak_kotlin.data.remote

import AdsModelItem
import CategoriesModelItem
import UsersModelItem
import com.h5190058.berkay_catak_kotlin.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service {

    //https://www.gorenduyan.com/admin/api/odev/categories.json
    @GET("categories.json")
    suspend fun getAllCategories(): Response<List<CategoriesModelItem>>

    @GET("users.json")
    suspend fun getAllUsers(): Response<List<UsersModelItem>>

    @GET("ads.json")
    suspend fun getAllAds(): Response<List<AdsModelItem>>

    companion object  {

        fun build(): Service {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient =  OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHtppClient)
                .build()

            return retrofit.create(Service::class.java)
        }
    }
}