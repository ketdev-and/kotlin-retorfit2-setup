package com.example.retorfit_setup

import com.google.gson.annotations.SerializedName

data class RetroData(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)