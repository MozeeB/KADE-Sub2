package com.zeeb.footballmatchschedule.screen.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zeeb.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        favFragmentVP.adapter = FavPagerAdapter(childFragmentManager)
        tabFavFragmentTL.setupWithViewPager(favFragmentVP)

        backFavIV.setOnClickListener {
            activity?.onBackPressed()
        }

    }


}
