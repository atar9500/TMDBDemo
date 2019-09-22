package com.atar.tmdbdemo.api

import com.atar.tmdbdemo.api.responses.DiscoverMoviesResponse
import retrofit2.Call
import retrofit2.http.*

interface TMDBApi {

    @GET("discover/movie")
    fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("sort_by") sortBy: String,
        @Query("primary_release_date.gte") fromReleaseDate: String,
        @Query("primary_release_date.lte") toReleaseDate: String
    ): Call<DiscoverMoviesResponse>

}