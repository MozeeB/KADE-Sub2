package com.zeeb.footballmatchschedule.screen.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.local.database.database
import com.zeeb.footballmatchschedule.data.local.model.FavoriteTeam
import com.zeeb.footballmatchschedule.helper.view.FavTeamItemView
import kotlinx.android.synthetic.main.fragment_fav_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavTeamFragment : Fragment() {

    private val adapterTeam = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        showFavoriteTeam()

        rvFavTeam.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterTeam
        }
    }

    private fun showFavoriteTeam() {
        context?.database?.use {
            adapterTeam.clear()
            val result = select(FavoriteTeam.TABLE_FAV_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isEmpty()){
                Toast.makeText(context, "No data Found" , Toast.LENGTH_LONG).show()
            }else{
                favorite.map {
                    adapterTeam.add(FavTeamItemView(it))
                    adapterTeam.notifyDataSetChanged()
                }

            }

        }
    }

    override fun onResume() {
        super.onResume()
        showFavoriteTeam()
    }
}
