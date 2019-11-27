package com.zeeb.footballmatchschedule.helper.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import kotlinx.android.synthetic.main.item_match.view.*

class LastMatchItemView(private val lastMatchDomain: LastMatchDomain) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {

        val strEvent = viewHolder.itemView.strEventItemMatchTV
        val strLeague = viewHolder.itemView.strLeagueItemMatchTV
        val intHomeScore = viewHolder.itemView.intHomeScoreItemMatchTV
        val intAwayScore = viewHolder.itemView.intAwayScoreItemMatchTV
        val dateEvent = viewHolder.itemView.dateEventItemMatchTV

        strEvent.text = lastMatchDomain.strEvent
        strLeague.text = lastMatchDomain.strLeague
        val homeScore = lastMatchDomain.intHomeScore
        val awayScore =  lastMatchDomain.intAwayScore

        if (homeScore == null){
            intHomeScore.text = "0"
        }else{
            intHomeScore.text = homeScore
        }
        if (awayScore == null){
            intAwayScore.text = "0"
        }else{
            intAwayScore.text = awayScore
        }
        dateEvent.text = lastMatchDomain.dateEvent

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf(
                "id" to lastMatchDomain.idEvent,
                "idHome" to lastMatchDomain.idHomeTeam,
                "idAway" to lastMatchDomain.idAwayTeam)

            it.findNavController().navigate(R.id.action_detailFragment2_to_detailMatchFragment, bundle)
        }


    }

    override fun getLayout(): Int = R.layout.item_match

}