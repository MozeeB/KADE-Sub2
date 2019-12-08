package com.zeeb.footballmatchschedule.screen.detail.nextmatch

import com.zeeb.footballmatchschedule.data.mapper.LastMatchMapper
import com.zeeb.footballmatchschedule.data.model.LastMatchModel
import com.zeeb.footballmatchschedule.data.repository.NextMatchRepositoryImpl
import com.zeeb.footballmatchschedule.data.response.TopNextMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchRepositoryImplTest {
    private val globalService = Mockito.mock(GlobalService::class.java)
    private val nextMatchMapper = Mockito.mock(LastMatchMapper::class.java)

    lateinit var repositoryImpl: NextMatchRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoryImpl = NextMatchRepositoryImpl(globalService, nextMatchMapper)
    }

    @Test
    fun getNextMatch() {
        `when`(globalService.getNextMatch("4387")).thenReturn(
            Single.just(
                TopNextMatchResponse(
                    listOf(
                        LastMatchModel(
                            "",
                            "Houston Rockets vs Phoenix Suns",
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
        repositoryImpl.getNextMactH("4387")
            .test()
            .assertComplete()
    }
}