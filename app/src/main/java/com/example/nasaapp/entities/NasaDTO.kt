package com.example.nasaapp.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class NasaDTO(
    @SerializedName("date") var date: String,
    @SerializedName("explanation") var explanation: String,
    @SerializedName("hdurl") var hdurl: String,
    @SerializedName("media_type") var mediaType: String,
    @SerializedName("service_version") var serviceVersion: String,
    @SerializedName("title") var title: String,
    @SerializedName("url") var url: String
) : Parcelable
