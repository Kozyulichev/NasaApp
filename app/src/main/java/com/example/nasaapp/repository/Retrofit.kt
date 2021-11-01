package com.example.nasaapp.repository

import android.media.Image
import com.example.nasaapp.entities.NasaDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nasa.gov/"
private const val TOKEN = "g4VGTPUodvUyso8c6hpNzGrEoZ6gd0G1olr4MfXM"

interface Repository {

    suspend fun getApi(): NasaDTO
    suspend fun getDateFromDate(date: String): NasaDTO
    suspend fun getEarthPhoto(lon: String, lat: String, date: String):Image
}

class RepositoryImpl : Repository {
    private val nasaApi = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NasaAPI::class.java)

    override suspend fun getApi(): NasaDTO {
        return nasaApi.getNasaApi(TOKEN)
    }

    override suspend fun getDateFromDate(date: String): NasaDTO {
        return nasaApi.getDataFromDate(date, TOKEN)
    }

    override suspend fun getEarthPhoto(lon: String, lat: String, date: String):Image{
        return nasaApi.getEarthPhoto(lon, lat, date, TOKEN)
    }
}