package com.zeeb.footballmatchschedule.screen.detail.standing


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
import com.zeeb.footballmatchschedule.helper.view.StandingItemView
import kotlinx.android.synthetic.main.fragment_standing.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment : Fragment() {

    private val vm:StandingVM by inject()

    private val adapterStanding = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.standingState.observe(this, startObserver)
        val id = Hawk.get("id", "")
        vm.getStandings(id)

        rvStanding.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter =  adapterStanding
        }
    }

    private val startObserver = Observer<StandingState>{  StandingState ->
        when(StandingState){
            is StandingDataLoaded ->{
                adapterStanding.clear()
                StandingState.standingDomain.map {
                    adapterStanding.add(StandingItemView(it))
                }
            }
            is ErrorState ->{

            }
        }
    }

}
