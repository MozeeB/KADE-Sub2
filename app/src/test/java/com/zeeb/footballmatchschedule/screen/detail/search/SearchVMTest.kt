package com.zeeb.footballmatchschedule.screen.detail.search

import com.zeeb.footballmatchschedule.data.repository.SearchRepository
import com.zeeb.footballmatchschedule.data.response.TopSearchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchVMTest{
    private val league = "English Premier League"

    @Mock
    private lateinit var service: GlobalService

    @Mock
    private lateinit var topResponse: TopSearchResponse

    @Mock
    private lateinit var repository: SearchRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDetailLeague() {
        val responseTeams = topResponse

        runBlocking {
            `when`(service.searchMatch(league))
        }
            .thenReturn(Single.just(responseTeams))
        repository.searchEvent(league)


    }
}