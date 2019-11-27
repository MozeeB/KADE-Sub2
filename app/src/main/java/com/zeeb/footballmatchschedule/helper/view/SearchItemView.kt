package com.zeeb.footballmatchschedule.helper.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.domain.SearchDomain
import kotlinx.android.synthetic.main.item_match.view.*

class SearchItemView (private val searchDomain: SearchDomain) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val strEvent = viewHolder.itemView.strEventItemMatchTV
        val strLeague = viewHolder.itemView.strLeagueItemMatchTV
        val intHomeScore = viewHolder.itemView.intHomeScoreItemMatchTV
        val intAwayScore = viewHolder.itemView.intAwayScoreItemMatchTV
        val dateEvent = viewHolder.itemView.dateEventItemMatchTV

        val homeScore = searchDomain.intHomeScore
        val awayScore = searchDomain.intAwayScore

        if (homeScore.isNullOrBlank() || awayScore.isNullOrBlank()){
            intHomeScore.text = "0"
            intAwayScore.text = "0"
        }else{
            intHomeScore.text = homeScore
            intAwayScore.text = awayScore
        }
        strEvent.text = searchDomain.strEvent
        strLeague.text = searchDomain.strLeague
        dateEvent.text = searchDomain.dateEvent

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf("id" to searchDomain.idEvent)
            it.findNavController().navigate(R.id.action_searchFragment_to_detailMatchFragment, bundle)

        }
    }

    override fun getLayout(): Int = R.layout.item_match
}