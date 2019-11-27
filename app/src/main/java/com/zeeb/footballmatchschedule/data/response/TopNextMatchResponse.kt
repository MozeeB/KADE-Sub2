package com.zeeb.footballmatchschedule.data.response

import com.zeeb.footballmatchschedule.data.model.LastMatchModel

data class TopNextMatchResponse(
    val events: List<LastMatchModel>
)