package com.example.universitiestest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitiestest.model.University
import com.example.universitiestest.repository.UniversityRepository
import kotlinx.coroutines.launch

class UniversityViewModel(private val universityRepository: UniversityRepository):ViewModel() {
    private val _universitiesList=MutableLiveData<List<University>>()
    val universitiesList:LiveData<List<University>>
    get() = _universitiesList

    suspend fun getUniversitiesList(){
        viewModelScope.launch {
            val response=universityRepository.getUniversities()
            _universitiesList.value=response
        }
    }
}