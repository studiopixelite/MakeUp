package com.makeup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makeup.model.data.Item
import com.makeup.model.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

// The MainViewModel is meant for the MainActivity and all fragments connected to it
class MainViewModel(private val repository: Repository ): ViewModel() {

    /**
     */
    val itemResponse: MutableLiveData<Response<Item>> = MutableLiveData()
    val itemsResponse: MutableLiveData<Response<List<Item>>> = MutableLiveData()

    fun getItem() {
        viewModelScope.launch {
            val response = repository.getItem()
            itemResponse.value = response
        }
    }

    fun getItems(id: Int) {
        viewModelScope.launch {
            val response = repository.getItems(id)
            itemsResponse.value = response

        }
    }

}