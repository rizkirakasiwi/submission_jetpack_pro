package com.dicoding.pilem.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.pilem.R
import com.dicoding.pilem.data.FilmData
import com.dicoding.pilem.databinding.DetailFragmentBinding
import com.dicoding.pilem.util.imageHelper.imageLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding : DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("DetailFragment", "on detail fragment")
        binding = DetailFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val data = arguments?.getParcelable<FilmData>("data")
            if (data != null) {
                viewModel.addDetail(data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.detailData.observe(viewLifecycleOwner, {
            setView(it)
        })
    }

    private fun setView(filmData: FilmData){
        GlobalScope.launch(Dispatchers.Main){
            imageLoader(filmData.poster, requireContext()).into(binding.posterImage)
        }

        binding.titleText.text = filmData.title
        binding.descText.text = filmData.description
        binding.genreText.text = getString(R.string.genre_s, filmData.genre.joinToString())
        binding.ratingText.text = filmData.rating.toString()
        binding.yearText.text = getString(R.string.year_s, filmData.year)
    }


}