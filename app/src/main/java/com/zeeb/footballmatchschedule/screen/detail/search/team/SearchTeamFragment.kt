package com.zeeb.footballmatchschedule.screen.detail.search.team


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.helper.view.SearchTeamItemView
import kotlinx.android.synthetic.main.fragment_search_team.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class SearchTeamFragment : Fragment(), SearchView.OnQueryTextListener {

    private val vm:SearchTeamVM by inject()

    private val adapterSearchTeam = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.searchTeamState.observe(this, startObserver)

        searchTeamsFragmentSV.setOnQueryTextListener(this)
        backSearchTeamsFragmentIV.setOnClickListener {
            activity?.onBackPressed()
        }

        searchTeamsFragmentRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearchTeam

        }

    }

    private val startObserver = Observer<SearchTeamState>{ response ->
        when(response){
            is SearchTeamDataLoaded ->{
                adapterSearchTeam.clear()
                response.teamsDomain.map {
                    adapterSearchTeam.add(SearchTeamItemView(it))
                }
            }
            is ErrorState ->{

            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        vm.getSearchTeam(newText!!)
        return true
    }

}
