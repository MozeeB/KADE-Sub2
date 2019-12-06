package com.zeeb.footballmatchschedule.screen.detail.nextmatch

import com.zeeb.footballmatchschedule.data.repository.NextMatchRepository
import com.zeeb.footballmatchschedule.data.response.TopNextMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchVMTest{
    private val match = "Liverpool vs Everton"

    @Mock
    private lateinit var service: GlobalService

    @Mock
    private lateinit var topResponse: TopNextMatchResponse

    @Mock
    private lateinit var repository: NextMatchRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDetailLeague() {
        val responseTeams = topResponse

        runBlocking {
            `when`(service.getNextMatch(match))
        }
            .thenReturn(Single.just(responseTeams))
        repository.getNextMactH(match)


    }
}