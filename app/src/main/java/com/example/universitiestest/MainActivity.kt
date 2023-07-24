package com.example.universitiestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universitiestest.adapter.UniversityAdapter
import com.example.universitiestest.api.RetrofitBuilder
import com.example.universitiestest.databinding.ActivityMainBinding
import com.example.universitiestest.repository.UniversityRepository
import com.example.universitiestest.viewModel.UniversityViewModel
import com.example.universitiestest.viewModelFactory.UniversityViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel:UniversityViewModel by viewModels {
        UniversityViewModelFactory(UniversityRepository(RetrofitBuilder.apiService))

    }
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter:UniversityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.universityViewModel=viewModel
        setupRecyclerView()
        fetchData()
    }

    private fun setupRecyclerView(){
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= UniversityAdapter()
        binding.recyclerView.adapter=adapter

    }

    private fun fetchData(){
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getUniversitiesList()
            displayUniversitiesList()
        }
    }

    private fun displayUniversitiesList(){
        viewModel.universitiesList.observe(this, Observer {it ->
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
}