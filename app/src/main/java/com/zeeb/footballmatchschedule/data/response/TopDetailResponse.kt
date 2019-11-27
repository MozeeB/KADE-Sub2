package com.zeeb.footballmatchschedule.data.response

import com.zeeb.footballmatchschedule.data.model.FootballDetailModel

data class TopDetailResponse (
    val leagues: List<FootballDetailModel>
)