package com.example.universitiestest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.universitiestest.databinding.ItemUniversityBinding
import com.example.universitiestest.model.University

class UniversityAdapter: RecyclerView.Adapter<ViewHolder>() {
     var universityList= ArrayList<University>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding =
            ItemUniversityBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return universityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(universityList[position])
    }

    fun setList(universities: List<University>) {
        universityList.clear()
        universityList.addAll(universities)

    }
}