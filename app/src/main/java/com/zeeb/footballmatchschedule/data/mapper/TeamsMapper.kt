package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.TeamsModel
import com.zeeb.footballmatchschedule.domain.TeamsDomain

class TeamsMapper : BaseMapper<TeamsModel, TeamsDomain> {
    override fun mapToDomain(model: TeamsModel): TeamsDomain {
        return TeamsDomain(
            model.idTeam,
            model.strTeam,
            model.strSport,
            model.strLeague,
            model.idLeague,
            model.strTeamBadge,
            model.strDescriptionEN
        )
    }

    override fun mapToModel(domain: TeamsDomain): TeamsModel {
        return TeamsModel(
            domain.idTeam,
            domain.strTeam,
            domain.strSport,
            domain.strLeague,
            domain.idLeague,
            domain.strTeamBadge,
            domain.strDescriptionEN
        )
    }
}