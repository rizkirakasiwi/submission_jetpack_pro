package com.dicoding.pilem.util

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.pilem.ui.movie.MovieFragment
import com.dicoding.pilem.ui.tvshow.TvShowFragment

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragment = listOf(MovieFragment(), TvShowFragment())

    override fun getItemCount(): Int = fragment.count()

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}