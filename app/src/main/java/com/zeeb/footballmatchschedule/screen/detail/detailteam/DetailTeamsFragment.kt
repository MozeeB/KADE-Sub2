package com.zeeb.footballmatchschedule.screen.detail.detailteam


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.squareup.picasso.Picasso
import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import kotlinx.android.synthetic.main.fragment_detail_teams.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamsFragment : Fragment() {

    private val vm: DetailTeamVM by inject()

    private var data: TeamsDomain? = null

    private var idTeam:Any? = null


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
        idTeam = arguments?.getString("idTeam")
        vm.getDetailTeams(idTeam.toString())

        vm.checkFavState(idTeam.toString())

        backDetailTemasIV.setOnClickListener {
            activity?.onBackPressed()
        }
        searchTeamsDetailIV.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailTeamsFragment_to_searchTeamFragment)
        }

        addFavDetailTeamsIV.setOnClickListener {
            if (addFavDetailTeamsIV.background.constantState == ContextCompat.getDrawable(
                    this.context!!,
                    R.drawable.ic_heart_1
                )!!.constantState) {

                vm.addToFav(data!!)
            }else{
                vm.removeFav(idTeam.toString())
            }
        }
    }

    private val startObservable = Observer<DetailTeamState> { response ->
        when (response) {
            is DetailTeamDataLoaded -> {
                data = response.teamsDomain[0]

                Picasso.get().load(data?.strTeamBadge).into(badgeDetailTeamsIV)
                strTeamDetailTeamsTV.text = data?.strTeam
                strLeagueDetailTeamsTV.text = data?.strLeague
                descriptionDetailTeamsTV.text = data?.strDescriptionEN

            }
            is FavAddSuccess ->{
                addFavDetailTeamsIV.background = ContextCompat.getDrawable(
                    this@DetailTeamsFragment.context!!,
                    R.drawable.ic_heart_2
                )

                Toast.makeText(context, "Add to Fav", Toast.LENGTH_LONG).show()

            }
            is FavRemoveSuccess ->{
                addFavDetailTeamsIV.background = ContextCompat.getDrawable(
                    this@DetailTeamsFragment.context!!,
                    R.drawable.ic_heart_1
                )
                Toast.makeText(context, "remove to Fav", Toast.LENGTH_LONG).show()

            }
            is FavTeamDataFound ->{
                response.teamsDomain.map {
                    if (it.idTeam == idTeam){
                        addFavDetailTeamsIV.background = ContextCompat.getDrawable(
                            this@DetailTeamsFragment.context!!,
                            R.drawable.ic_heart_2
                        )
                    }
                }
            }
            is ErrorState -> {
                Toast.makeText(context, response.msg, Toast.LENGTH_LONG).show()

            }
        }
    }
}


