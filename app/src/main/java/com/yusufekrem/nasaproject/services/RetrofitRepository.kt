package com.yusufekrem.nasaproject.services

class RetrofitRepository {
    var retrofitClient : RetrofitService = RetrofitClient().retrofitService
    suspend fun getData() = retrofitClient.getMarsData()
}