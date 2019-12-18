package com.zeeb.footballmatchschedule.screen.detail.team


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.hawk.Hawk
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.helper.view.TeamsItemView
import kotlinx.android.synthetic.main.fragment_team.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment() {

    private val vm:TeamVM by inject()

    private val adapterTeams = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.teamsState.observe(this, startObserver)
        val id = Hawk.get("id", "")
        vm.getTeams(id)

        rvTeams.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterTeams
        }
    }

    private val startObserver = Observer<TeamsState>{ response ->
        when(response){
            is TeamsDataLoaded ->{
                adapterTeams.clear()
                response.teamsDomain.map {
                    adapterTeams.add(TeamsItemView(it))
                }

            }
            is ErrorState ->{

            }
        }
    }
}
