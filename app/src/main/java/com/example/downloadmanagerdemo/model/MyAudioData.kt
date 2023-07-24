package com.example.downloadmanagerdemo.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
@Parcelize
@Serializable
data class MyAudioData (

    val id: Int,
    val title: String,
    val thumbnail: String,
    val media_url: String,
    val artiste: String
):  Parcelable