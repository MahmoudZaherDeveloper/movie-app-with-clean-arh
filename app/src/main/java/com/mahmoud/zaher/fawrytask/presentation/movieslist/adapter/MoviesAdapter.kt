package com.mahmoud.zaher.fawrytask.presentation.movieslist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.zaher.fawrytask.core.IMAGE_URL
import com.mahmoud.zaher.fawrytask.core.utils.getProgressDrawable
import com.mahmoud.zaher.fawrytask.core.utils.loadImage
import com.mahmoud.zaher.fawrytask.core.view.viewBinding
import com.mahmoud.zaher.fawrytask.databinding.ItemMovieListBinding
import com.mahmoud.zaher.fawrytask.domain.model.Movie


class MoviesAdapter(
    private val onItemClicked: (Movie) -> Unit
) :
    ListAdapter<Movie, MoviesAdapter.Holder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.viewBinding(ItemMovieListBinding::inflate))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class Holder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Movie: Movie, onItemClicked: (Movie) -> Unit) {
            with(binding) {
                container.setOnClickListener { onItemClicked(Movie) }
                tvName.text = Movie.movieName
                imageView.loadImage(
                    IMAGE_URL + Movie.posterPath
                )
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}