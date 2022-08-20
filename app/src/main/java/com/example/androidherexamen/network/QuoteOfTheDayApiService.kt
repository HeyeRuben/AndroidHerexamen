package com.example.androidherexamen.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.quotable.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface QuoteOfTheDayApiService {
    @GET("random")
    fun getPropertiesAsync(): Deferred<QuoteOfTheDayProperty>
}

object QuoteOfTheDayApi {
    val retrofitService: QuoteOfTheDayApiService by lazy {
        retrofit.create(QuoteOfTheDayApiService::class.java)
    }
}
