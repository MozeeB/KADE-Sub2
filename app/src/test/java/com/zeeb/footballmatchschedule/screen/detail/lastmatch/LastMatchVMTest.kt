package com.zeeb.footballmatchschedule.screen.detail.lastmatch

import com.zeeb.footballmatchschedule.data.repository.LastMatchRepository
import com.zeeb.footballmatchschedule.data.response.TopLastMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class LastMatchVMTest{

    private val match = "Liverpool vs Everton"

    @Mock
    private lateinit var service: GlobalService

    @Mock
    private lateinit var topResponse: TopLastMatchResponse

    @Mock
    private lateinit var lastMatchVM: LastMatchVM


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastMatchVM = Mockito.mock(LastMatchVM::class.java)
    }

    @Test
    fun getDetailLeague() {
        val responseTeams = topResponse

        runBlocking {
            `when`(service.getLastMatch(match))
        }
            .thenReturn(Single.just(responseTeams))
        lastMatchVM.getLastMatch(match)


    }
}