package com.zeeb.footballmatchschedule.data.response

import com.zeeb.footballmatchschedule.data.model.StandingModel

data class TopStandingResponse(
    val table:List<StandingModel>
)