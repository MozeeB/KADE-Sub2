package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.SearchModel
import com.zeeb.footballmatchschedule.domain.SearchDomain

class SearchMapper : BaseMapper<SearchModel, SearchDomain> {
    override fun mapToDomain(model: SearchModel): SearchDomain {
        return SearchDomain(
            model.idEvent,
            model.strEvent,
            model.strSport,
            model.strLeague,
            model.strSeason,
            model.strHomeTeam,
            model.strAwayTeam,
            model.intHomeScore,
            model.intRound,
            model.intAwayScore,
            model.strHomeGoalDetails,
            model.strAwayGoalDetails,
            model.dateEvent,
            model.strTime
        )
    }

    override fun mapToModel(domain: SearchDomain): SearchModel {
        return SearchModel(
            domain.idEvent,
            domain.strEvent,
            domain.strSport,
            domain.strLeague,
            domain.strSeason,
            domain.strHomeTeam,
            domain.strAwayTeam,
            domain.intHomeScore,
            domain.intRound,
            domain.intAwayScore,
            domain.strHomeGoalDetails,
            domain.strAwayGoalDetails,
            domain.dateEvent,
            domain.strTime
        )
    }
}