package com.example.acromineapp.network

import com.example.acromineapp.model.AcronymResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymApi {
    @GET("dictionary.py")
    fun getShortAcronymDetails(
        @Query("sf") search: String
    ): Call<List<AcronymResponseObject>>

    @GET("dictionary.py")
    fun getLongAcronymDetails(
        @Query("lf") search: String
    ): Call<List<AcronymResponseObject>>
}