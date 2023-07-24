package com.example.universitiestest.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.universitiestest.repository.UniversityRepository
import com.example.universitiestest.viewModel.UniversityViewModel

class UniversityViewModelFactory(private val universityRepository: UniversityRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UniversityViewModel::class.java)){
            return UniversityViewModel(universityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}