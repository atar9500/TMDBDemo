package com.atar.tmdbdemo.api.responses

import com.atar.tmdbdemo.Movie
import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0,
    @SerializedName("results") val results: List<Movie> = emptyList()
)