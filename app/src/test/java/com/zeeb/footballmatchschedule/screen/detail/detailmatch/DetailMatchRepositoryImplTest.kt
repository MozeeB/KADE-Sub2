package com.zeeb.footballmatchschedule.screen.detail.detailmatch


import com.zeeb.footballmatchschedule.data.mapper.DetailMatchMapper
import com.zeeb.footballmatchschedule.data.mapper.TeamLogoMapper
import com.zeeb.footballmatchschedule.data.model.DetailMatchModel
import com.zeeb.footballmatchschedule.data.repository.DetailMatchRepositoryImpl
import com.zeeb.footballmatchschedule.data.response.TopDetailMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailMatchRepositoryImplTest {

    val globalService = Mockito.mock(GlobalService::class.java)
    val detailMatchMapper = Mockito.mock(DetailMatchMapper::class.java)
    val teamMapper = Mockito.mock(TeamLogoMapper::class.java)

    private lateinit var repository: DetailMatchRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = DetailMatchRepositoryImpl(globalService, detailMatchMapper, teamMapper)
    }

    @Test
    fun getDetailMatch() {
        `when`(globalService.getDetailMatch("441613")).thenReturn(
            Single.just(
                TopDetailMatchResponse(
                    listOf(
                        DetailMatchModel(
                            "441613",
                            "Liverpool vs Swansea",
                            "Soccer",
                            "English Premier League",
                            "1415",
                            "Liverpool",
                            "Swansea",
                            "4",
                            "19",
                            "1",
                            "69':Own  Jonjo Shelvey;61': Adam Lallana;51': Adam Lallana;33': Alberto Moreno;",
                            "52': Gylfi Sigurdsson;",
                            "2014-12-29",
                            "20:00:00+00:00"
                        ),
                        DetailMatchModel(
                            "648011",
                            "Boston Celtics vs Denver Nuggets",
                            "Basketball",
                            "NBA",
                            "1920",
                            "Boston Celtics",
                            "Denver Nuggets",
                            "108",
                            "0",
                            "95",
                            null,
                            null,
                            "2019-12-07",
                            "01:00:00"
                        )
                    )
                )
            )
        )
        repository.getDetailMatch("441613")
            .test()
            .assertComplete()

    }
}