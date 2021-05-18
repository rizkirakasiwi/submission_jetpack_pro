package com.dicoding.pilem.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.pilem.R
import com.dicoding.pilem.data.FilmData
import com.dicoding.pilem.databinding.MovieItemRowBinding
import com.dicoding.pilem.util.imageHelper.imageLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopularMovieAdapter: ListAdapter<FilmData, PopularMovieViewHolder>(PopularMovieDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val popularMovieView = MovieItemRowBinding.inflate(inflater, parent, false)
        return PopularMovieViewHolder(popularMovieView)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("data" to getItem(position))
            it.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

}

class PopularMovieViewHolder(private val binding : MovieItemRowBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(filmData: FilmData){
        with(binding){
            titleText.text = filmData.title
            yearText.text = filmData.year
            genreText.text = filmData.genre.joinToString()
            ratingText.text = filmData.rating.toString()
        }

        GlobalScope.launch(Dispatchers.Main){
            imageLoader(filmData.poster, itemView.context).into(binding.bannerImage)
        }


    }

}


class PopularMovieDiffUtil:DiffUtil.ItemCallback<FilmData>() {
    override fun areItemsTheSame(oldItem: FilmData, newItem: FilmData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilmData, newItem: FilmData): Boolean {
        return oldItem.id == newItem.id
    }
}


