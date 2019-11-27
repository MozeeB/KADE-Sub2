package com.zeeb.footballmatchschedule.data.response

import com.zeeb.footballmatchschedule.data.model.LastMatchModel

data class TopLastMatchResponse (
    val events: List<LastMatchModel>
)