package com.example.retorfit_setup

import retrofit2.Response
import retrofit2.http.GET

interface RetroInt {
    @GET("/todos")
   suspend fun getTodo() : Response<List<RetroData>>
}