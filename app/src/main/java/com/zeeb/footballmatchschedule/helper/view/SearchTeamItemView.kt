package com.zeeb.footballmatchschedule.helper.view

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import kotlinx.android.synthetic.main.item_teams.view.*

class SearchTeamItemView(private val teamsDomain: TeamsDomain) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val badge = viewHolder.itemView.badgeItemTeamsIV
        val title = viewHolder.itemView.titleItemTeamsTV
        val deskripsi = viewHolder.itemView.overviewItemTeams

        Picasso.get().load(teamsDomain.strTeamBadge).into(badge)
        title.text = teamsDomain.strTeam
        deskripsi.text = teamsDomain.strDescriptionEN

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf("idTeam" to teamsDomain.idTeam)
            it.findNavController().navigate(R.id.action_searchTeamFragment_to_detailTeamsFragment, bundle)
        }
    }

    override fun getLayout(): Int = R.layout.item_teams
}