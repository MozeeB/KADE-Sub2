package com.zeeb.footballmatchschedule.screen.detail.detailteam


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.local.database.database
import com.zeeb.footballmatchschedule.data.local.model.FavoriteTeam
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import kotlinx.android.synthetic.main.fragment_detail_teams.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamsFragment : Fragment() {

    private val vm:DetailTeamVM by inject()

    private var data: TeamsDomain? = null

    private var isFavorite: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_teams, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        vm.detailTeamState.observe(this, startObservable)
        val idTeam = arguments?.getString("idTeam")
        vm.getDetailTeams(idTeam!!)

        backDetailTemasIV.setOnClickListener {
            activity?.onBackPressed()
        }
        searchTeamsDetailIV.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailTeamsFragment_to_searchTeamFragment)
        }

        addFavDetailTeamsIV.setOnClickListener {
            if (isFavorite) removeToFavorite() else addToFavorite()
            isFavorite = !isFavorite

        }
    }

    private val startObservable = Observer<DetailTeamState>{ response ->
        when(response){
            is DetailTeamDataLoaded ->{
                data = response.teamsDomain[0]

                Picasso.get().load(data?.strTeamBadge).into(badgeDetailTeamsIV)
                strTeamDetailTeamsTV.text = data?.strTeam
                strLeagueDetailTeamsTV.text = data?.strLeague
                descriptionDetailTeamsTV.text = data?.strDescriptionEN

            }
            is ErrorState ->{

            }
        }
    }

    private fun addToFavorite() {
        try {
            context?.database?.use {
                insert(
                   FavoriteTeam.TABLE_FAV_TEAM,
                    FavoriteTeam.ID_TEAM to data?.idTeam,
                    FavoriteTeam.STR_TEAM to data?.strTeam,
                    FavoriteTeam.STR_SPORT to data?.strSport,
                    FavoriteTeam.STR_LEAGUE to data?.strLeague,
                    FavoriteTeam.ID_LEAGUE to data?.idLeague,
                    FavoriteTeam.STR_TEAMBADGE to data?.strTeamBadge,
                    FavoriteTeam.STR_DESCRIPTION_EN to data?.strDescriptionEN

                )
                Toast.makeText(context, "Add to Fav", Toast.LENGTH_LONG).show()

            }
        }catch (e: SQLiteConstraintException){
            Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }

    private fun removeToFavorite() {
        val id = data?.idLeague
        try {
            context?.database?.use {
                delete(FavoriteTeam.TABLE_FAV_TEAM, "( ID_TEAM = {idTeam} )",
                    "idTeam" to id.toString())
            }
            Toast.makeText(context, "remove to Fav", Toast.LENGTH_LONG).show()
        }catch (e: SQLiteConstraintException){
            Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }
}
