package com.zeeb.footballmatchschedule.data.response

import com.zeeb.footballmatchschedule.data.model.SearchModel

data class TopSearchResponse(
    val event: List<SearchModel>
)