package com.example.bstest23.networkCommunication

class ApiConstants {
    companion object {
        const val BASE_URL: String = "https://api.github.com"
        const val API_PRODUCTS: String = "$BASE_URL/search/repositories?q=android"
    }
}