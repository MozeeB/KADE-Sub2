package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.StandingModel
import com.zeeb.footballmatchschedule.domain.StandingDomain

class StandingMapper : BaseMapper<StandingModel, StandingDomain>{
    override fun mapToDomain(model: StandingModel): StandingDomain {
        return StandingDomain(
            model.name,
            model.teamid,
            model.played,
            model.goalsfor,
            model.goalsagainst,
            model.goalsdifference,
            model.win,
            model.draw,
            model.loss,
            model.total
        )
    }

    override fun mapToModel(domain: StandingDomain): StandingModel {
        return StandingModel(
            domain.name,
            domain.teamid,
            domain.played,
            domain.goalsfor,
            domain.goalsagainst,
            domain.goalsdifference,
            domain.win,
            domain.draw,
            domain.loss,
            domain.total
        )
    }
}