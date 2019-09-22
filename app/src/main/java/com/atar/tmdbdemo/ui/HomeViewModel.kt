package com.atar.tmdbdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atar.tmdbdemo.Movie
import com.atar.tmdbdemo.api.responses.DiscoverMoviesResponse
import com.atar.tmdbdemo.api.MoviesRepository

class HomeViewModel : ViewModel() {

    private val mMoviesRepository = MoviesRepository
    private val mLatestMovies: MutableLiveData<List<Movie>> = mMoviesRepository.latestMovies
    private val mPopularMovies: MutableLiveData<List<Movie>> = mMoviesRepository.popularMovies

    init {
        fetchLatestMovies()
        fetchPopularMovies()
    }

    fun fetchLatestMovies() {
        mMoviesRepository.getLatestMovies()
    }

    fun fetchPopularMovies() {
        mMoviesRepository.getPopularMovies()
    }

    fun getLatestMovies(): MutableLiveData<List<Movie>> = mLatestMovies

    fun getPopularMovies(): MutableLiveData<List<Movie>> = mPopularMovies

}
