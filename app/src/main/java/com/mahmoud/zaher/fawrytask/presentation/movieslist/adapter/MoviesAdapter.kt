package com.mahmoud.zaher.fawrytask.presentation.movieslist.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.zaher.fawrytask.core.view.viewBinding
import com.mahmoud.zaher.fawrytask.databinding.ItemMovieListBinding
import com.mahmoud.zaher.fawrytask.domain.model.Movie


class MoviesAdapter(
    private val data: List<Movie> = listOf(),
    private val onItemClicked: (Movie) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.Holder>() {

    class Holder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(Movie: Movie, onItemClicked: (Movie) -> Unit) {
            with(binding) {
                container.setOnClickListener { onItemClicked(Movie) }
                tvName.text = "${Movie.id} ${Movie.movieName}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.viewBinding(ItemMovieListBinding::inflate))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], onItemClicked)
    }

    override fun getItemCount() = data.size


}