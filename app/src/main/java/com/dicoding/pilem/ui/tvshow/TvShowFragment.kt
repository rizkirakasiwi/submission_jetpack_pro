package com.dicoding.pilem.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.pilem.databinding.TvShowFragmentBinding

class TvShowFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowFragment()
    }

    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var binding: TvShowFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val popularMovieDummy = viewModel.popularTvShowData
        val movieData = viewModel.tvShowData

        val popularMovieAdapter = PopularTvShowAdapter()
        val movieAdapter = TvShowAdapter ()


        popularMovieAdapter.submitList(popularMovieDummy)
        movieAdapter.submitList(movieData)

        binding.recyclerviewPopularTvShow.adapter = popularMovieAdapter
        binding.recyclerviewTvshow.adapter = movieAdapter

    }

}