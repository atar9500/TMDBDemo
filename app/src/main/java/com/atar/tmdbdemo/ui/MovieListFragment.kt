package com.atar.tmdbdemo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.atar.tmdbdemo.Movie
import com.atar.tmdbdemo.R
import kotlinx.android.synthetic.main.movie_list_fragment.*

class MovieListFragment : Fragment() {

    /**
     * Data
     */
    private lateinit var mViewModel: HomeViewModel
    private lateinit var mLatestMoviesAdapter: MovieAdapter
    private lateinit var mPopularMoviesAdapter: MovieAdapter
    private val mLatestMoviesObserver = Observer<List<Movie>> {
        mLatestMoviesAdapter.updateMovies(it)
    }
    private val mPopularMoviesObserver = Observer<List<Movie>> {
        mPopularMoviesAdapter.updateMovies(it)
    }

    /**
     * Fragment Functions
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLatestMoviesAdapter = MovieAdapter(context!!)
        mPopularMoviesAdapter = MovieAdapter(context!!)
        val gridSpacing = resources.getDimensionPixelOffset(R.dimen.list_spacing)


        frml_latest_movies_list.adapter = mLatestMoviesAdapter
        frml_latest_movies_list.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
        frml_latest_movies_list.addItemDecoration(ListDivider(gridSpacing, 1, true))

        frml_popular_movies_list.adapter = mPopularMoviesAdapter
        frml_popular_movies_list.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
        frml_popular_movies_list.addItemDecoration(ListDivider(gridSpacing, 1, true))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mViewModel.getLatestMovies().observe(this, mLatestMoviesObserver)
        mViewModel.getPopularMovies().observe(this, mPopularMoviesObserver)
    }

    override fun onDestroy() {
        mViewModel.getLatestMovies().removeObserver(mLatestMoviesObserver)
        mViewModel.getPopularMovies().removeObserver(mPopularMoviesObserver)
        super.onDestroy()
    }

}
