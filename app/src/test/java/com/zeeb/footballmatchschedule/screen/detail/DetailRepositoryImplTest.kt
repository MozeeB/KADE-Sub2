package com.zeeb.footballmatchschedule.screen.detail

import com.zeeb.footballmatchschedule.data.mapper.FootballDetailMapper
import com.zeeb.footballmatchschedule.data.model.FootballDetailModel
import com.zeeb.footballmatchschedule.data.repository.FootballDetailRepository
import com.zeeb.footballmatchschedule.data.repository.FootballDetailRepositoryImpl
import com.zeeb.footballmatchschedule.data.response.TopDetailResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class DetailRepositoryImplTest {

    private val globalService = Mockito.mock(GlobalService::class.java)
    private val detailMapper = Mockito.mock(FootballDetailMapper::class.java)

    private lateinit var footballDetailRepositoryImpl: FootballDetailRepositoryImpl
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        footballDetailRepositoryImpl = FootballDetailRepositoryImpl(globalService, detailMapper)
    }

    @Test
    fun getDetailLeague() {
        `when`(globalService.getDetailLiga("4328")).thenReturn(
            Single.just(
                TopDetailResponse(
                    listOf(
                        FootballDetailModel(
                            "4328",
                            "English Premier League",
                            "1992",
                            "England",
                            "",
                            ""
                        )
                    )
                )
            )
        )

        footballDetailRepositoryImpl.getDetailLiga("4328")
            .test()
            .assertComplete()
    }
}