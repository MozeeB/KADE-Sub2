package com.zeeb.footballmatchschedule.screen.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FavPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        FavMatchFragment(),
        FavTeamFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Match"
            else -> "Team"
        }
    }
}