package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.TeamLogoModel
import com.zeeb.footballmatchschedule.domain.TeamLogoDomain

class TeamLogoMapper : BaseMapper<TeamLogoModel, TeamLogoDomain> {
    override fun mapToDomain(logoModel: TeamLogoModel): TeamLogoDomain {
        return TeamLogoDomain(
            logoModel.idTeam,
            logoModel.strTeamBadge
        )
    }

    override fun mapToModel(logoDomain: TeamLogoDomain): TeamLogoModel {
        return TeamLogoModel(
            logoDomain.idTeam,
            logoDomain.strTeamBadge
        )
    }
}