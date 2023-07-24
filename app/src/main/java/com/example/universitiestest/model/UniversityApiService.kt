package com.example.universitiestest.model

import retrofit2.http.GET

interface UniversityApiService {
    @GET("search?country=United+States")
    suspend fun getUniversities():List<University>
}