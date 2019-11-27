package com.zeeb.footballmatchschedule.data.mapper

import com.zeeb.footballmatchschedule.data.model.FootballDetailModel
import com.zeeb.footballmatchschedule.domain.FootballDetailDomain

class FootballDetailMapper : BaseMapper<FootballDetailModel, FootballDetailDomain> {
    override fun mapToDomain(model: FootballDetailModel): FootballDetailDomain {
        return FootballDetailDomain(
            model.idLeague,
            model.strLeague,
            model.intFormedYear,
            model.strCountry,
            model.strDescriptionEN,
            model.strLogo
        )
    }

    override fun mapToModel(domain: FootballDetailDomain): FootballDetailModel {
        return FootballDetailModel(
            domain.idLeague,
            domain.strLeague,
            domain.intFormedYear,
            domain.strCountry,
            domain.strDescriptionEN,
            domain.strLogo
        )
    }
}