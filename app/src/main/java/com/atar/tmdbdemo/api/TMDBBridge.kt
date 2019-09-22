package com.atar.tmdbdemo.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBBridge {

    private const val BASE_URL = "http://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .method(original.method(), original.body())
        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val instance: TMDBApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(TMDBApi::class.java)
    }

}