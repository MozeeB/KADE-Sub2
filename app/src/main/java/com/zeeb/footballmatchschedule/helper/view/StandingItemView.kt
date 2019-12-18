package com.zeeb.footballmatchschedule.helper.view

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.domain.StandingDomain
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingItemView(val standingDomain: StandingDomain) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val name = viewHolder.itemView.nameItemStandingTV
        val played = viewHolder.itemView.playedItemStandingTV
        val win = viewHolder.itemView.winItemStandingTV
        val draw = viewHolder.itemView.drawItemStandingTV
        val loss = viewHolder.itemView.lossItemStandingTV

        name.text = standingDomain.name
        played.text = standingDomain.played.toString()
        win.text = standingDomain.win.toString()
        draw.text = standingDomain.draw.toString()
        loss.text = standingDomain.loss.toString()
    }

    override fun getLayout(): Int = R.layout.item_standing
}