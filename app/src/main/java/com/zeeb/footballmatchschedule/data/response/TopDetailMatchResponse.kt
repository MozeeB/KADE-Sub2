package com.zeeb.footballmatchschedule.data.response

import com.zeeb.footballmatchschedule.data.model.DetailMatchModel

data class TopDetailMatchResponse(
    val events: List<DetailMatchModel>

)