package com.yusufekrem.nasaproject.services

import com.yusufekrem.nasaproject.model.Data
import retrofit2.http.GET

interface RetrofitService{
    @GET("api/v1/rovers/curiosity/photos?sol=1000&api_key=gWaMJmUaTnFAsRGSVHA8L1YrigSMMGmaAyvkyCdq")
    suspend fun getMarsData(): Data
}