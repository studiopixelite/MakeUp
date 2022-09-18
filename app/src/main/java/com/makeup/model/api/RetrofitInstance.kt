package com.makeup.model.api

import com.makeup.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/*
This is the Retrofit instance that defines the url to be loaded and creates the API
 */
object RetrofitInstance {
    private val retrofit  by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //This function creates the api to used with create()
    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}