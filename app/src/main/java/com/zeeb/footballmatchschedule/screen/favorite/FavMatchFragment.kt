package com.zeeb.footballmatchschedule.screen.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.local.database.database
import com.zeeb.footballmatchschedule.data.local.model.FavoriteLastMatch
import com.zeeb.footballmatchschedule.helper.view.FavMatchItemView
import kotlinx.android.synthetic.main.fragment_fav_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavMatchFragment : Fragment() {

    private var adapterFavLastMatch = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        showFavoriteMatch()

        favRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterFavLastMatch
        }

    }

    private fun showFavoriteMatch() {
        context?.database?.use {
            adapterFavLastMatch.clear()
            val result = select(FavoriteLastMatch.TABLE_FAV_MATCH)
            val favorite = result.parseList(classParser<FavoriteLastMatch>())
            if (favorite.isEmpty()){
                Toast.makeText(context, "No data Found" , Toast.LENGTH_LONG).show()
            }else{
                favorite.map {
                    adapterFavLastMatch.add(FavMatchItemView(it))
                    adapterFavLastMatch.notifyDataSetChanged()
                }

            }

        }
    }

    override fun onResume() {
        super.onResume()
        showFavoriteMatch()
    }
}
