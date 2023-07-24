package com.example.universitiestest.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.universitiestest.databinding.ItemUniversityBinding
import com.example.universitiestest.model.University

class ViewHolder(private val binding: ItemUniversityBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(university:University){
        binding.university=university
    }
}