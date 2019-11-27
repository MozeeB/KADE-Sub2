package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.DetailMatchModel
import com.zeeb.footballmatchschedule.domain.DetailMatchDomain

class DetailMatchMapper : BaseMapper<DetailMatchModel, DetailMatchDomain> {
    override fun mapToDomain(model: DetailMatchModel): DetailMatchDomain {
        return DetailMatchDomain(
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

    override fun mapToModel(domain: DetailMatchDomain): DetailMatchModel {
        return DetailMatchModel(
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