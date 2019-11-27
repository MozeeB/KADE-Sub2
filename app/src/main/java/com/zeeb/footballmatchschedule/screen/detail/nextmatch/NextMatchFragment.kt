package com.zeeb.footballmatchschedule.screen.detail.nextmatch


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
import com.zeeb.footballmatchschedule.helper.view.NextMatchItemView
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment() {

    private val vm: NextMatchVM by inject()

    private val adapterNextMatch = GroupAdapter<ViewHolder>()

    var id = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.nextMatchState.observe(this, startObserver)

        rv_nextMatch.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterNextMatch
        }
        id = Hawk.get("id", "")
        vm.getNextMatch(id)
    }

    private val startObserver = Observer<NextMatchState> { onState ->
        when (onState) {
            is NextMatchDataLoaded -> {
                adapterNextMatch.clear()
                onState.lastMatchDomain.map {
                    adapterNextMatch.add(NextMatchItemView(it))
                }
            }
            is ErrorState -> {
                Toast.makeText(context, "No data in Next Match", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getNextMatch(id)

    }
}
