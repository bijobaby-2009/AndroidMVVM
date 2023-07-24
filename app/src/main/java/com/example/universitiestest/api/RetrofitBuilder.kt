package com.example.universitiestest.api

import com.example.universitiestest.model.UniversityApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val BASE_URL = "http://universities.hipolabs.com/"
    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService:UniversityApiService by lazy {
        retrofit.create(UniversityApiService::class.java)
    }
}