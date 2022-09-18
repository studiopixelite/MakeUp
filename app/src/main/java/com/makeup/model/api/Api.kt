package com.makeup.model.api

import retrofit2.Response
import com.makeup.model.data.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    // This function was created to get a single item but was not later used in the project
    @GET("api/v1/products.json")
    suspend fun getItem (): Response<Item>

    /* This function gets the values from the item data class, suspend is used
    because we will call this method in a Coroutine
    */
    @GET("api/v1/products.json")
    suspend fun getItems (
        @Query("id") id: Int
    ): Response<List<Item>>
}