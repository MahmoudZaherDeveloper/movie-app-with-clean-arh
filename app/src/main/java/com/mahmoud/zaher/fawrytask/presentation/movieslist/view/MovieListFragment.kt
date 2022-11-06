package com.mahmoud.zaher.fawrytask.presentation.movieslist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mahmoud.zaher.fawrytask.databinding.FragmentMovieListBinding
import com.mahmoud.zaher.fawrytask.presentation.movieslist.viewmodel.MovieListScreenState
import com.mahmoud.zaher.fawrytask.presentation.movieslist.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private val viewModel: MovieListViewModel by viewModels()

    //private val binding by viewBinding(FragmentUsersListBinding::bind)
    lateinit var binding: FragmentMovieListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeScreenState()
    }

    private fun observeScreenState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect { state ->
                    when (state) {
                        is MovieListScreenState.Initial -> Unit
                        // is MovieListScreenState.Loading -> showLoading()
                        // is MovieListScreenState.LoadingNextPage -> handleShowFooterProgress(state.characters)
                        // is MovieListScreenState.Success -> handleSuccessState(state.characters)
                        is MovieListScreenState.Success -> Log.v(
                            "TAG",
                            "observeScreenState: ${state.movies}"
                        )
                        else -> {
                        }
                    }
                }
            }
        }
    }

}