package com.example.nasaapp.repository

import android.media.Image
import com.example.nasaapp.entities.NasaDTO
import retrofit2.http.GET
import retrofit2.http.Query

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

    @GET("planetary/earth/imagery?")
    suspend fun getEarthPhoto(
        @Query("lon") lon: String,
        @Query("lat") lat: String,
        @Query("date") date: String,
        @Query("api_key") token: String
    ):Image
}