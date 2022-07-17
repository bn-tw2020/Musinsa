package com.homework.myapplication.data.network

import com.homework.myapplication.data.model.ListResponse
import retrofit2.http.GET

interface MusinsaService {

    @GET("interview/list.json")
    suspend fun getHomeData(): ListResponse?
}