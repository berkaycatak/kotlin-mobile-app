package com.h5190058.berkay_catak_kotlin.ui.category;
import CategoriesModelItem
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190058.berkay_catak_kotlin.data.repository.CategoriesRepository
import com.h5190058.berkay_catak_kotlin.util.ResourceStatus
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val categoryRepository: CategoriesRepository =
        CategoriesRepository()

    init {
        getAllCategories()
    }

    var categoriesLiveData = MutableLiveData<List<CategoriesModelItem>>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var error =    MutableLiveData<Throwable>()

    fun getAllCategories()  = viewModelScope.launch {

        categoryRepository.getAllCategories()
            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {

                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                        Log.e("viewModel","loading")
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("viewModel","success")
                        Log.e("viewModel",it.data.toString())
                        categoriesLiveData.postValue(it.data!!)
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

