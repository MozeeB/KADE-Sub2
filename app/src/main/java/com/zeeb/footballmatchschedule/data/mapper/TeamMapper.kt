package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.TeamModel
import com.zeeb.footballmatchschedule.domain.TeamDomain

class TeamMapper : BaseMapper<TeamModel, TeamDomain> {
    override fun mapToDomain(model: TeamModel): TeamDomain {
        return TeamDomain(
            model.idTeam,
            model.strTeamBadge
        )
    }

    override fun mapToModel(domain: TeamDomain): TeamModel {
        return TeamModel(
            domain.idTeam,
            domain.strTeamBadge
        )
    }
}