package com.atar.tmdbdemo.api

import androidx.lifecycle.MutableLiveData
import com.atar.tmdbdemo.Constants
import com.atar.tmdbdemo.Movie
import com.atar.tmdbdemo.api.responses.DiscoverMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

object MoviesRepository {

    private val mTMDBApi: TMDBApi = TMDBBridge.instance
    private val mPopularMoviesCallback = object : Callback<DiscoverMoviesResponse> {
        override fun onFailure(call: Call<DiscoverMoviesResponse>, t: Throwable) {
            popularMovies.value = emptyList()
        }

        override fun onResponse(
            call: Call<DiscoverMoviesResponse>,
            response: Response<DiscoverMoviesResponse>
        ) {
            if (response.isSuccessful) {
                popularMovies.value = response.body()?.results ?: emptyList()
            }
        }

    }
    private val mLatestMoviesCallback = object : Callback<DiscoverMoviesResponse> {
        override fun onFailure(call: Call<DiscoverMoviesResponse>, t: Throwable) {
            latestMovies.value = emptyList()
        }

        override fun onResponse(
            call: Call<DiscoverMoviesResponse>,
            response: Response<DiscoverMoviesResponse>
        ) {
            if (response.isSuccessful) {
                latestMovies.value = response.body()?.results ?: emptyList()
            }
        }

    }

    val popularMovies = MutableLiveData<List<Movie>>().apply {
        value = emptyList()
    }
    val latestMovies = MutableLiveData<List<Movie>>().apply {
        value = emptyList()
    }

    fun getPopularMovies() {
        val fromDate = getDateAgo(1)
        val toDate = getDateAgo(0)
        discoverMovies(
            Constants.POPULARITY_SORT_DESC,
            fromDate,
            toDate,
            mPopularMoviesCallback
        )
    }

    fun getLatestMovies() {
        val fromDate = getDateAgo(52)
        val toDate = getDateAgo(0)
        discoverMovies(
            Constants.POPULARITY_SORT_DESC,
            fromDate,
            toDate,
            mLatestMoviesCallback
        )
    }

    private fun getDateAgo(weeksAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_YEAR, -weeksAgo)
        val format = SimpleDateFormat(Constants.RELEASE_DATE_FORMAT, Locale.getDefault())
        return format.format(calendar.time)
    }

    private fun discoverMovies(
        sortBy: String,
        fromDate: String,
        toDate: String,
        callback: Callback<DiscoverMoviesResponse>
    ) {
        mTMDBApi.discoverMovies(Constants.API_KEY, sortBy, fromDate, toDate)
            .enqueue(callback)
    }

}