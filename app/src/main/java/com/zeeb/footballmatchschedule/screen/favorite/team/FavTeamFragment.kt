package com.zeeb.footballmatchschedule.screen.favorite.team


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.helper.view.FavTeamItemView
import kotlinx.android.synthetic.main.fragment_fav_team.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class FavTeamFragment : Fragment() {

    private val adapterTeam = GroupAdapter<ViewHolder>()

    private val vm:FavTeamVM by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.favTeamState.observe(this, starObserver)
        vm.getTeamFav()

        rvFavTeam.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterTeam
        }
    }


    private val starObserver = Observer<FavTeamState>{ response ->
        when(response){
            is FavTeamDataLoaded ->{
                adapterTeam.clear()
                response.teamsDomain.map {
                    adapterTeam.add(FavTeamItemView(it))
                }
            }
            is ErrorState ->{

            }
        }
    }
}
