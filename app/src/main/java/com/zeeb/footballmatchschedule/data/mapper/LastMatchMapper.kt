package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.LastMatchModel
import com.zeeb.footballmatchschedule.domain.LastMatchDomain

class LastMatchMapper : BaseMapper<LastMatchModel, LastMatchDomain> {
    override fun mapToDomain(model: LastMatchModel): LastMatchDomain {
        return LastMatchDomain(
            model.idEvent,
            model.strEvent,
            model.strLeague,
            model.strSeason,
            model.strHomeTeam,
            model.strAwayTeam,
            model.intHomeScore,
            model.intRound,
            model.intAwayScore,
            model.dateEvent,
            model.strTime,
            model.idHomeTeam,
            model.idAwayTeam
        )
    }

    override fun mapToModel(domain: LastMatchDomain): LastMatchModel {
        return LastMatchModel(
            domain.idEvent,
            domain.strEvent,
            domain.strLeague,
            domain.strSeason,
            domain.strHomeTeam,
            domain.strAwayTeam,
            domain.intHomeScore,
            domain.intRound,
            domain.intAwayScore,
            domain.dateEvent,
            domain.strTime,
            domain.idHomeTeam,
            domain.idAwayTeam
        )
    }
}