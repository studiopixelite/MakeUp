package com.makeup.model.repository

import com.makeup.model.api.RetrofitInstance
import com.makeup.model.data.Item
import retrofit2.Response

class Repository {

    suspend fun getItem() : Response<Item> {
        return RetrofitInstance.api.getItem()
    }

    suspend fun getItems() : Response<List<Item>> {
        return RetrofitInstance.api.getItems()
    }

}