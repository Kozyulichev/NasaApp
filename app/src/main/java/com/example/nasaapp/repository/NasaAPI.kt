package com.example.nasaapp.repository

import com.example.nasaapp.entities.NasaDTO
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NasaAPI {

    @GET("planetary/apod?")
    suspend fun getNasaApi(
        @Query("api_key") token: String
    ): NasaDTO

    @GET("planetary/apod?")
    suspend fun getDataFromDate(
        @Query("date") date: String,
        @Query("api_key") token: String
    ): NasaDTO
}