package com.h5190058.berkay_catak_kotlin.data.repository

import UsersModelItem
import com.h5190058.berkay_catak_kotlin.data.UsersDataSource
import com.h5190058.berkay_catak_kotlin.data.remote.UsersRetrofitDataSource
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow

class UsersRepository {
    private var usersDataSource: UsersDataSource?=null

    init {
        usersDataSource = UsersRetrofitDataSource()
    }

    fun getAllUsers(): Flow<Resource<List<UsersModelItem>>>
    {
        return usersDataSource!!.getAllUsers()
    }
}