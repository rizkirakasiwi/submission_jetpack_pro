package com.dicoding.pilem.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.pilem.R
import com.dicoding.pilem.databinding.HomeFragmentBinding
import com.dicoding.pilem.util.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding : HomeFragmentBinding
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        binding.viewPager.adapter = homeViewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.movie)
                1 -> tab.text = getString(R.string.tv_show)
            }
        }.attach()
    }



}