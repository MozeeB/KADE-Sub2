package com.zeeb.footballmatchschedule.helper.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import kotlinx.android.synthetic.main.item_match.view.*

class NextMatchItemView(private val nextMatchDomain: LastMatchDomain) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {

        val strEvent = viewHolder.itemView.strEventItemMatchTV
        val strLeague = viewHolder.itemView.strLeagueItemMatchTV
        val intHomeScore = viewHolder.itemView.intHomeScoreItemMatchTV
        val intAwayScore = viewHolder.itemView.intAwayScoreItemMatchTV
        val dateEvent = viewHolder.itemView.dateEventItemMatchTV

        strEvent.text = nextMatchDomain.strEvent
        strLeague.text = nextMatchDomain.strLeague
        val homeScore = nextMatchDomain.intHomeScore
        val awayScore =  nextMatchDomain.intAwayScore

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

        dateEvent.text = nextMatchDomain.dateEvent

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf(
                "id" to nextMatchDomain.idEvent,
                "idHome" to nextMatchDomain.idHomeTeam,
                "idAway" to nextMatchDomain.idAwayTeam)

            it.findNavController().navigate(R.id.action_detailFragment2_to_detailMatchFragment, bundle)

        }

    }

    override fun getLayout(): Int = R.layout.item_match

}