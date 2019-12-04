package com.zeeb.footballmatchschedule.screen.detail.detailmatch


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.local.database.database
import com.zeeb.footballmatchschedule.data.local.model.FavoriteLastMatch
import kotlinx.android.synthetic.main.fragment_detail_match.*
import org.koin.android.ext.android.inject
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment(), View.OnClickListener {

    private val vm:DetailMatchVM by inject()

    private var isFavorite: Boolean = false

    var idEvent :String? = null
    var dateEvent = ""
    var homeTeam = ""
    var awayTeam = ""
    var scoreHome = ""
    var scoreAway = ""
    var nameEvent = ""
    var league = ""

    var idHome = ""
    var idAway = ""

    var id = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.detailMatchStatev.observe(this, startObserver)



        id  = arguments?.getString("id").toString()
        idHome = arguments?.getString("idHome").toString()
        idAway = arguments?.getString("idAway").toString()


        vm.getDetailMatch(id)
        vm.getLogoHome(idHome)
        vm.getLogoAway(idAway)

        backDetailMatchFragmentIV.setOnClickListener{
            this.activity?.onBackPressed()
        }
        favDetailMatchFragmentIB.setOnClickListener(this)
        progressBarHolderLoginCL.visibility = View.VISIBLE


    }

    override fun onResume() {
        super.onResume()
        vm.getDetailMatch(id)

    }

    private val startObserver = Observer<DetailMatchState>{ matchState ->

        when(matchState){
            is ErrorState ->{
                Toast.makeText(context, matchState.msg, Toast.LENGTH_LONG).show()
                progressBarHolderLoginCL.visibility = View.GONE

            }
            is LogoHomeLoaded ->{
                Picasso.get().load(matchState.teamDomain[0].strTeamBadge).into(logoHomeDetailMatchFragmentIV)
                progressBarHolderLoginCL.visibility = View.GONE

            }
            is LogoAwayLoaded ->{
                Picasso.get().load(matchState.teamDomain[0].strTeamBadge).into(logoAwayDetailMatchFragmentIV)
                progressBarHolderLoginCL.visibility = View.GONE

            }
            is DetailMatchDataLoaded ->{

                val data = matchState.detailMatchDomain[0]

                idEvent = data.idEvent
                dateEvent = data.dateEvent
                homeTeam = data.strHomeTeam
                awayTeam = data.strAwayTeam
                scoreAway = data.intAwayScore.toString()
                scoreHome = data.intHomeScore.toString()
                nameEvent = data.strEvent
                league = data.strLeague

                val homeScore = matchState.detailMatchDomain[0].intHomeScore
                val awayScore = matchState.detailMatchDomain[0].intAwayScore

                val homeGoal = matchState.detailMatchDomain[0].strHomeGoalDetails
                val awayoGoal = matchState.detailMatchDomain[0].strAwayGoalDetails

                if (homeScore == null || awayScore == null || homeGoal.isNullOrBlank() || awayoGoal.isNullOrBlank()){
                    intHomeScoreDetailMatchTV.text = "0"
                    intAwayScoreDetailMatchTV.text = "0"

                    intHomeScoreDownDetailMatchTV.text = "0"
                    intAwayScoreDownDetailMatchTV.text = "0"

                    strHomeGoalDetailsDetailMatchTV.text = ""
                    strAwayGoalDetailsDetailMatchTV.text = ""
                }else{
                    intHomeScoreDetailMatchTV.text = homeScore
                    intAwayScoreDetailMatchTV.text = awayScore

                    intHomeScoreDownDetailMatchTV.text = homeScore
                    intAwayScoreDownDetailMatchTV.text = awayScore

                    strHomeGoalDetailsDetailMatchTV.text = homeGoal
                    strAwayGoalDetailsDetailMatchTV.text = awayoGoal
                }

                strEventDetailMatchTV.text = matchState.detailMatchDomain[0].strEvent
                strLeagueDetailMatchTV.text = matchState.detailMatchDomain[0].strLeague
                strEventDownDetailMatchTV.text = matchState.detailMatchDomain[0].strEvent
                dateEventDetailMatchTV.text = matchState.detailMatchDomain[0].dateEvent
                strHomeTeamDetailMatchTV.text = matchState.detailMatchDomain[0].strHomeTeam
                strAwayTeamDetailMatchTV.text = matchState.detailMatchDomain[0].strAwayTeam



                progressBarHolderLoginCL.visibility = View.GONE


                favoriteState()


            }

        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.favDetailMatchFragmentIB ->{
                if (isFavorite) removeToFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
    }
    private fun setFavorite() {
        if (isFavorite) {
            favDetailMatchFragmentIB.background.constantState == ContextCompat.getDrawable(
                this.context!!, R.drawable.ic_favorite_red_24dp
            )!!.constantState
        } else {
            favDetailMatchFragmentIB.background.constantState == ContextCompat.getDrawable(
                this.context!!, R.drawable.ic_favorite_black_24dp
            )!!.constantState
        }
    }

    private fun addToFavorite() {
        try {
            context?.database?.use {
                insert(
                    FavoriteLastMatch.TABLE_FAV_MATCH,
                    FavoriteLastMatch.ID_EVENT to idEvent,
                    FavoriteLastMatch.DATE_EVENT to dateEvent,
                    FavoriteLastMatch.HOME_SCORE to scoreHome,
                    FavoriteLastMatch.AWAY_SCORE to scoreAway,
                    FavoriteLastMatch.NAME_EVENT to nameEvent,
                    FavoriteLastMatch.LEAGUE to league,
                    FavoriteLastMatch.ID_HOME_TEAM to idHome,
                    FavoriteLastMatch.ID_AWAY_TEAM to idAway)
                Toast.makeText(context, "Add to Fav", Toast.LENGTH_LONG).show()

            }
        }catch (e: SQLiteConstraintException){
            Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }

    private fun removeToFavorite() {
        try {
            context?.database?.use {
                delete(FavoriteLastMatch.TABLE_FAV_MATCH, "( ID_EVENT = {id_event} )",
                    "id_event" to idEvent!!)
            }
            Toast.makeText(context, "remove to Fav", Toast.LENGTH_LONG).show()
        }catch (e: SQLiteConstraintException){
            Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }

    private fun favoriteState(){
        context?.database?.use {
            val result = select(FavoriteLastMatch.TABLE_FAV_MATCH).whereArgs("( ID_EVENT = {id_event})", "id_event" to idEvent!!)
            val favoriteMatch = result.parseList(classParser<FavoriteLastMatch>())
            if (favoriteMatch.isNotEmpty()) {
                isFavorite = true
                favDetailMatchFragmentIB.background.constantState == ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_added_to_favorites
                )!!.constantState
            }else{
                isFavorite = false
                favDetailMatchFragmentIB.background.constantState == ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_add_to_favorites
                )!!.constantState
                Toast.makeText(context, favoriteMatch[0].idEvent, Toast.LENGTH_LONG).show()
            }
        }
    }
}
