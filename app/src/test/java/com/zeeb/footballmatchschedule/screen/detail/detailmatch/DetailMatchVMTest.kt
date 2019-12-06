package com.zeeb.footballmatchschedule.screen.detail.detailmatch

import com.zeeb.footballmatchschedule.data.repository.DetailMatchRepository
import com.zeeb.footballmatchschedule.data.response.TopDetailMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailMatchVMTest{
    private val league = "English Premier League"

    @Mock
    private lateinit var service: GlobalService

    @Mock
    private lateinit var topResponse: TopDetailMatchResponse

    @Mock
    private lateinit var repository: DetailMatchRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDetailLeague() {
        val responseTeams = topResponse

        runBlocking {
            `when`(service.getDetailMatch(league))
        }
            .thenReturn(Single.just(responseTeams))
        repository.getDetailMatch(league)


    }
}