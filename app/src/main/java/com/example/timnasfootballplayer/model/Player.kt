package com.example.timnasfootballplayer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val description: String,
    val photo: Int,
    val position: String,
    val height: String,
    val weight: String,
    val club: String
) : Parcelable