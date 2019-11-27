package com.zeeb.footballmatchschedule.screen.detail.detailmatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso

import com.zeeb.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_detail_match.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment(), View.OnClickListener {

    private val vm:DetailMatchVM by inject()

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

        val id  = arguments?.getString("id")
        val idHome = arguments?.getString("idHome").toString()
        val idAway = arguments?.getString("idAway").toString()

        vm.getDetailMatch(id!!)
        vm.getLogoHome(idHome)
        vm.getLogoAway(idAway)
        backDetailMatchFragmentIV.setOnClickListener(this)
    }

    private val startObserver = Observer<DetailMatchState>{ matchState ->
        when(matchState){

            is ErrorState ->{
                Toast.makeText(context, matchState.msg, Toast.LENGTH_LONG).show()
            }
            is LogoHomeLoaded ->{
                Picasso.get().load(matchState.teamDomain[0].strTeamBadge).into(logoHomeDetailMatchFragmentIV)
            }
            is LogoAwayLoaded ->{
                Picasso.get().load(matchState.teamDomain[0].strTeamBadge).into(logoAwayDetailMatchFragmentIV)
            }
            is DetailMatchDataLoaded ->{
                strEventDetailMatchTV.text = matchState.detailMatchDomain[0].strEvent
                strLeagueDetailMatchTV.text = matchState.detailMatchDomain[0].strLeague
                strEventDownDetailMatchTV.text = matchState.detailMatchDomain[0].strEvent
                dateEventDetailMatchTV.text = matchState.detailMatchDomain[0].dateEvent
                strHomeTeamDetailMatchTV.text = matchState.detailMatchDomain[0].strHomeTeam
                strAwayTeamDetailMatchTV.text = matchState.detailMatchDomain[0].strAwayTeam


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

            }

        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.backDetailMatchFragmentIV -> findNavController().navigate(R.id.action_detailMatchFragment_to_detailFragment2)
        }
    }

}
