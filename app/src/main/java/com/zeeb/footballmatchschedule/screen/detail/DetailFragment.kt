package com.zeeb.footballmatchschedule.screen.detail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.orhanobut.hawk.Hawk
import com.squareup.picasso.Picasso

import com.zeeb.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), View.OnClickListener {

    private val vm:DetailVM by inject()

    private var id = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBarHolderLoginCL.visibility = View.VISIBLE

        detailFragmentVP.adapter = MyPagerAdapter(childFragmentManager)
        tabDetailFragmentTL.setupWithViewPager(detailFragmentVP)


        vm.detailState.observe(this, startObserver)

        backDetailFragmentIV.setOnClickListener {
            this.activity?.onBackPressed()
        }

//        val id  = this.activity?.intent?.extras?.get("id").toString()
        id  = Hawk.get("idLeague", "")
        vm.getDetailLiga(id)
        Hawk.put("id", id)

        searchDetailFragmentIV.setOnClickListener(this)



    }

    @SuppressLint("SetTextI18n")
    private val startObserver = Observer<FootballDetailState>{ onState ->
        when(onState){
            is DetailDataLoaded -> {

                titleDetailFragmentTV.text = onState.footballDetailDomain[0].strLeague + " " + "(" + onState.footballDetailDomain[0].intFormedYear + ")"
                descriptionDetailFragmentTV.text = onState.footballDetailDomain[0].strDescriptionEN
                countryDetailFragmentTV.text = onState.footballDetailDomain[0].strCountry
                Picasso.get().load(onState.footballDetailDomain[0].strLogo).into(ligaDetailFragmentIV)
                progressBarHolderLoginCL.visibility = View.GONE

            }

            is ErrorState -> {
                Toast.makeText(context, "Detail Failed To Load", Toast.LENGTH_LONG).show()
                progressBarHolderLoginCL.visibility = View.GONE

            }
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.searchDetailFragmentIV -> findNavController().navigate(R.id.action_detailFragment2_to_searchFragment)
        }
    }


}
