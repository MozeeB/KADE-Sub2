package com.zeeb.footballmatchschedule.screen.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import com.zeeb.footballmatchschedule.R
import com.zeeb.footballmatchschedule.data.model.FootballLeagueModel
import com.zeeb.footballmatchschedule.helper.view.FootballLeagueItemView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    val footballAdapter = GroupAdapter<ViewHolder>()
    var footBallItemModel: MutableList<FootballLeagueModel> = mutableListOf()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadDataFootball()
        val layoutManagers = GridLayoutManager(context, 2)

        rv_league.apply {
            layoutManager = layoutManagers
            adapter = footballAdapter
        }

        toFavHomeFragmentIB.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteActivity)
        }

    }


    private fun loadDataFootball() {
        val id = resources.getStringArray(R.array.id_league)
        val foto = resources.obtainTypedArray(R.array.daftar_foto)
        val nama = resources.getStringArray(R.array.daftar_club)

        footBallItemModel.clear()
        for (i in nama.indices) {
            footBallItemModel.add(
                FootballLeagueModel(
                    id[i],
                    foto.getResourceId(i, 0),
                    nama[i]
                )
            )
            footballAdapter.add(FootballLeagueItemView(footBallItemModel))

        }

        foto.recycle()

    }

    override fun onResume() {
        super.onResume()
        footballAdapter.clear()
        loadDataFootball()
    }

}
