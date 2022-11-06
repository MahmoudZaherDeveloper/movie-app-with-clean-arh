package com.mahmoud.zaher.fawrytask.presentation.movieslist.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.zaher.fawrytask.R
import com.mahmoud.zaher.fawrytask.core.isInternetAvailable
import com.mahmoud.zaher.fawrytask.core.pagination.ScrollingPagination
import com.mahmoud.zaher.fawrytask.core.view.viewBinding
import com.mahmoud.zaher.fawrytask.databinding.FragmentMovieListBinding
import com.mahmoud.zaher.fawrytask.domain.model.Movie
import com.mahmoud.zaher.fawrytask.presentation.movieslist.adapter.MoviesAdapter
import com.mahmoud.zaher.fawrytask.presentation.movieslist.viewmodel.MovieListScreenState
import com.mahmoud.zaher.fawrytask.presentation.movieslist.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private val viewModel: MovieListViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieListBinding::bind)
    private val newMovies = mutableListOf<Movie>()
    private val moviesAdapter = MoviesAdapter(::onUserClicked)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }
        observeScreenState()
        handleShowFooterProgress()
    }

    private fun observeScreenState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is MovieListScreenState.Initial -> Unit
                        is MovieListScreenState.Loading -> showLoading()
                        is MovieListScreenState.LoadingNextPage -> handleNextPage(state.movies)
                        is MovieListScreenState.Success -> handleSuccessState(state.movies)
                        is MovieListScreenState.Error -> handleError(state.message)
                    }
                }
            }
        }
    }

    private fun handleShowFooterProgress() {
        binding.rvMovies.addOnScrollListener(object :
            ScrollingPagination(binding.rvMovies.layoutManager as LinearLayoutManager) {
            override fun loadMoreDate() {
                viewModel.loadMore()

            }
        })
    }

    private fun handleError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
        if (context?.applicationContext?.isInternetAvailable() == false)
            viewModel.loadCachedData()
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun handleSuccessState(movieResult: List<Movie>) {
        hideLoading()
        //  binding.rvMovies.adapter = MoviesAdapter(movieResult, ::onUserClicked)
        moviesAdapter.submitList(movieResult)
    }

    private fun handleNextPage(movieResult: List<Movie>) {
        newMovies.addAll(movieResult)
        hideLoading()
        //binding.rvMovies.adapter = MoviesAdapter(newMovies.distinct(), ::onUserClicked)
        moviesAdapter.submitList(newMovies.distinct())
    }

    private fun onUserClicked(movie: Movie) {
        Toast.makeText(context, movie.movieName, Toast.LENGTH_SHORT).show()
    }
}