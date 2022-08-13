package com.example.androidherexamen.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://zenquotes.io/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface QuoteOfTheDayApiService{
    @GET("today")
    fun getProperties():
            Call<String>
}

object QuoteOfTheDayApi {
    val retrofitService : QuoteOfTheDayApiService by lazy {
        retrofit.create(QuoteOfTheDayApiService::class.java)
    }
}
