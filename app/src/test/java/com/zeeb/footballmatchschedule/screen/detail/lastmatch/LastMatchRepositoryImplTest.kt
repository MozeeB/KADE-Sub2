package com.zeeb.footballmatchschedule.screen.detail.lastmatch

import com.zeeb.footballmatchschedule.data.mapper.LastMatchMapper
import com.zeeb.footballmatchschedule.data.model.LastMatchModel
import com.zeeb.footballmatchschedule.data.repository.LastMatchRepositoryImpl
import com.zeeb.footballmatchschedule.data.response.TopLastMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class LastMatchRepositoryImplTest {

    private val globalService = Mockito.mock(GlobalService::class.java)
    private val lastMatchMapper = Mockito.mock(LastMatchMapper::class.java)

    private lateinit var lastMatchRepositoryImpl: LastMatchRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastMatchRepositoryImpl = LastMatchRepositoryImpl(globalService, lastMatchMapper)
    }

    @Test
    fun getLastMatch() {
        `when`(globalService.getLastMatch("4387")).thenReturn(
            Single.just(
                TopLastMatchResponse(
                    listOf(
                        LastMatchModel(
                            "648010",
                            "Detroit Pistons vs Indiana Pacers",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        )
                    )
                )
            )
        )
        lastMatchRepositoryImpl.getLastMatch("4387")
            .test()
            .assertComplete()


    }
}