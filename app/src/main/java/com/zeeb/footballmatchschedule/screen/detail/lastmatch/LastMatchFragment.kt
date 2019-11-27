package com.zeeb.footballmatchschedule.screen.detail.lastmatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.hawk.Hawk
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.helper.view.LastMatchItemView
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class LastMatchFragment : Fragment() {

    private val vm: LastMatchVM by inject()
    private val adapterLastMatch = GroupAdapter<ViewHolder>()

    var id = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.lastMatchState.observe(this, startObserver)

        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rv_lastmatch.apply {
            layoutManager = linearLayout
            adapter = adapterLastMatch
        }

        id = Hawk.get("id", "")
        vm.getLastMatch(id)


    }

    private val startObserver = Observer<LastMatchState> { onState ->
        when (onState) {
            is LastMatchDataLoaded -> {
                adapterLastMatch.clear()
                onState.lastMatchDomain.map {
                    adapterLastMatch.add(LastMatchItemView(it))
                }
            }
            is ErrorState -> {
                Toast.makeText(context, "Failed To Load", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getLastMatch(id)

    }

}
