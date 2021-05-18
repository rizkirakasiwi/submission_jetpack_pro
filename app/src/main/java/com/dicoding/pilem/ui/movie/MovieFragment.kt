package com.dicoding.pilem.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.pilem.databinding.MovieFragmentBinding


class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding : MovieFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val popularMovieDummy = viewModel.popularMovieData
        val movieData = viewModel.movieData

        val popularMovieAdapter = PopularMovieAdapter()
        val movieAdapter = MovieAdapter()


        popularMovieAdapter.submitList(popularMovieDummy)
        movieAdapter.submitList(movieData)

        binding.recyclerviewPopularMovie.adapter = popularMovieAdapter
        binding.recyclerviewMovie.adapter = movieAdapter

    }



}