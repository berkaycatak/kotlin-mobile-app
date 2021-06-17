package com.h5190058.berkay_catak_kotlin.ui.login;
import UsersModelItem
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190058.berkay_catak_kotlin.data.repository.UsersRepository
import com.h5190058.berkay_catak_kotlin.util.ResourceStatus
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val usersRepository: UsersRepository =
        UsersRepository()

    init {
        getAllUsers()
    }

    var usersLiveData = MutableLiveData<List<UsersModelItem>>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var error =    MutableLiveData<Throwable>()

    fun getAllUsers()  = viewModelScope.launch {

        usersRepository.getAllUsers()
            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {

                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                        Log.e("viewModel","loading")
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("viewModel","success")
                        Log.e("viewModel",it.data.toString())
                        usersLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        Log.e("viewModel","error")
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}

