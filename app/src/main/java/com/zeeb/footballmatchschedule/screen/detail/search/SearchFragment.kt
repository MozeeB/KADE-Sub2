package com.zeeb.footballmatchschedule.screen.detail.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.helper.view.SearchItemView
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() , View.OnClickListener, SearchView.OnQueryTextListener {

    private val vm:SearchVM by inject()

    private val adapterSearch = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.searchState.observe(this, startObserver)
        backSearchFragmentIV.setOnClickListener(this)
        searchFragmentSV.setOnQueryTextListener(this)

        searchFragmentRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterSearch
        }

        backSearchFragmentIV.setOnClickListener {
            this.activity?.onBackPressed()
        }
    }

    private val startObserver = Observer<SearchState>{ onstate ->
        when(onstate){
            is SearchDataLoaded ->{
                adapterSearch.clear()
                onstate.searchDomain.map {
                    adapterSearch.add(SearchItemView(it))
                }
            }
            is ErrorState ->{

            }
        }

    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        vm.searchEvent(newText!!)
        return true
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.backSearchFragmentIV ->  findNavController().navigate(R.id.action_searchFragment_to_detailFragment2)
        }
    }


}
