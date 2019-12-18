package com.zeeb.footballmatchschedule.helper.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.local.model.FavoriteLastMatch
import kotlinx.android.synthetic.main.item_match.view.*

class FavMatchItemView(private val favoriteLastMatch: FavoriteLastMatch) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val strEvent = viewHolder.itemView.strEventItemMatchTV
        val strLeague = viewHolder.itemView.strLeagueItemMatchTV
        val intHomeScore = viewHolder.itemView.intHomeScoreItemMatchTV
        val intAwayScore = viewHolder.itemView.intAwayScoreItemMatchTV
        val dateEvent = viewHolder.itemView.dateEventItemMatchTV

//        strEvent.text = favoriteLastMatch[position].strEvent
//        strLeague.text = favoriteLastMatch[position].strLeague
//
//        val home = favoriteLastMatch[position].homeScore
//        val away = favoriteLastMatch[position].awayScore
        strEvent.text = favoriteLastMatch.strEvent
        strLeague.text = favoriteLastMatch.strLeague

        val home = favoriteLastMatch.homeScore
        val away = favoriteLastMatch.awayScore
        when {
            home == "null" -> {
                intHomeScore.text = "0"

            }
            away == "null" -> {
                intAwayScore.text = "0"
            }
            else -> {
                intHomeScore.text = home
                intAwayScore.text = away
            }
        }

//        dateEvent.text = favoriteLastMatch[position].dateEvent
        dateEvent.text = favoriteLastMatch.dateEvent


        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf("id" to favoriteLastMatch.idEvent,
                "idHome" to favoriteLastMatch.idHomeTeam,
                "idAway" to favoriteLastMatch.idAwayTeam)
            it.findNavController().navigate(R.id.action_favoriteFragment_to_detailMatchFragment2, bundle)
        }
    }

    override fun getLayout(): Int = R.layout.item_match
}