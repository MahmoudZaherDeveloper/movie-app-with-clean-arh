package com.mahmoud.zaher.fawrytask.presentation.moviesdetails.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.mahmoud.zaher.fawrytask.R
import com.mahmoud.zaher.fawrytask.core.IMAGE_URL
import com.mahmoud.zaher.fawrytask.core.isInternetAvailable
import com.mahmoud.zaher.fawrytask.core.utils.loadImage
import com.mahmoud.zaher.fawrytask.core.view.viewBinding
import com.mahmoud.zaher.fawrytask.data.sources.remote.pojo.moviedetails.MovieDetails
import com.mahmoud.zaher.fawrytask.databinding.FragmentMovieDetailsBinding
import com.mahmoud.zaher.fawrytask.databinding.FragmentMovieListBinding
import com.mahmoud.zaher.fawrytask.domain.model.Movie
import com.mahmoud.zaher.fawrytask.presentation.moviesdetails.viewmodel.MovieDetailsScreenState
import com.mahmoud.zaher.fawrytask.presentation.moviesdetails.viewmodel.MovieDetailsViewModel
import com.mahmoud.zaher.fawrytask.presentation.movieslist.viewmodel.MovieListScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeScreenState()
    }

    private fun observeScreenState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is MovieDetailsScreenState.Initial -> Unit
                        is MovieDetailsScreenState.Loading -> showLoading()
                        is MovieDetailsScreenState.Success -> handleSuccessState(state.movies)
                        is MovieDetailsScreenState.Error -> handleError(state.message)
                    }
                }
            }
        }
    }

    private fun handleSuccessState(movieResult: MovieDetails) {
        hideLoading()
        binding.movieImg.loadImage(IMAGE_URL + movieResult.posterPath)
        binding.movieTitle.text = movieResult.originalTitle
        binding.movieDescription.text = movieResult.overview

    }

    private fun handleError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
        //  if (context?.applicationContext?.isInternetAvailable() == false)
        //viewModel.loadCachedData()
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }
}

