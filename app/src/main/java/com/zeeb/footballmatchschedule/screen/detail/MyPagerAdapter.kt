package com.zeeb.footballmatchschedule.screen.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zeeb.footballmatchschedule.screen.detail.lastmatch.LastMatchFragment
import com.zeeb.footballmatchschedule.screen.detail.nextmatch.NextMatchFragment
import com.zeeb.footballmatchschedule.screen.detail.standing.StandingFragment
import com.zeeb.footballmatchschedule.screen.detail.team.TeamFragment

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        LastMatchFragment(),
        NextMatchFragment(),
        StandingFragment(),
        TeamFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"
            1 -> "Next Match"
            2 -> "Standings"
            else -> "Team"
        }
    }
}