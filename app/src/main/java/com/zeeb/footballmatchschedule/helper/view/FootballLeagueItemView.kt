package com.zeeb.footballmatchschedule.helper.view

import androidx.navigation.findNavController
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.model.FootballLeagueModel
import kotlinx.android.synthetic.main.item_football.view.*

class FootballLeagueItemView(private val footballLeagueModel: List<FootballLeagueModel>) : Item() {


    override fun bind(viewHolder: ViewHolder, position: Int) {

        val photo = viewHolder.itemView.footballItemIV
        val nameLeague = viewHolder.itemView.nameFootballTV
        val item = viewHolder.itemView.footballItemCV

        Picasso.get().load(footballLeagueModel[position].foto).into(photo)
        nameLeague.text = footballLeagueModel[position].nama

        item.setOnClickListener {
            Hawk.put("idLeague", footballLeagueModel[position].id)
            it.findNavController().navigate(R.id.action_homeFragment_to_detailActivity)
        }

    }

    override fun getLayout(): Int = R.layout.item_football
}