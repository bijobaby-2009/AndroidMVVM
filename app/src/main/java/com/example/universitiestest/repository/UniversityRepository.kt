package com.example.universitiestest.repository

import com.example.universitiestest.model.University
import com.example.universitiestest.model.UniversityApiService

class UniversityRepository(private val universityApiService: UniversityApiService) {
    suspend fun getUniversities():List<University>{
        return universityApiService.getUniversities()
    }
}