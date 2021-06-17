package com.h5190058.berkay_catak_kotlin.data

import UsersModelItem
import com.h5190058.berkay_catak_kotlin.util.Resource
import kotlinx.coroutines.flow.Flow

interface UsersDataSource {
    fun getAllUsers(): Flow<Resource<List<UsersModelItem>>>
}