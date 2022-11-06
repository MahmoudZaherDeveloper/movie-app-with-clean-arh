package com.mahmoud.zaher.fawrytask.presentation.moviesdetails.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahmoud.zaher.fawrytask.R
import com.mahmoud.zaher.fawrytask.core.view.viewBinding
import com.mahmoud.zaher.fawrytask.databinding.FragmentMovieListBinding
import com.mahmoud.zaher.fawrytask.presentation.moviesdetails.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieListBinding::bind)

    private fun observeScreenState() {
        /*  viewLifecycleOwner.lifecycleScope.launch {
              repeatOnLifecycle(Lifecycle.State.STARTED) {
                  viewModel.screenState.collect { state ->
                      when (state) {
                          is MovieListScreenState.Initial -> Unit
                          is MovieListScreenState.Loading -> showLoading()
                          is MovieListScreenState.Success -> handleSuccessState(state.movies)
                          is MovieListScreenState.Error -> handleError(state.message)
                          else -> {}
                      }
                  }
              }
          }*/
    }
}

