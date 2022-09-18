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
     * itemsResponse collects the response from the API and
     * store it as a MutableLiveData, this avoid crashes as it is aware
     * of lifecycles (of an activity or fragment)
     */
    val itemResponse: MutableLiveData<Response<Item>> = MutableLiveData()
    val itemsResponse: MutableLiveData<Response<List<Item>>> = MutableLiveData()

    fun getItem() {
        viewModelScope.launch {
            val response = repository.getItem()
            itemResponse.value = response
        }
    }

    /*
    getItems() gets the response from the Repository and pass into
    itemsResponse
     */
    fun getItems() {
        viewModelScope.launch {
            val response = repository.getItems()
            itemsResponse.value = response

        }
    }

}