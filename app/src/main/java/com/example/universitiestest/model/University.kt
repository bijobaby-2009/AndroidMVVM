package com.example.universitiestest.model

data class University(
    val webPages: List<String>,
    val domains: List<String>,
    val country: String,
    val name: String,
    val stateProvince: String?,
    val alphaTwoCode: String
)