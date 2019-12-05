package com.zeeb.footballmatchschedule.screen.detail.nextmatch

import com.zeeb.footballmatchschedule.data.response.TopNextMatchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchVMTest{
    private val match = "Liverpool vs Everton"

    @Mock
    private lateinit var service: GlobalService

    @Mock
    private lateinit var topResponse: TopNextMatchResponse

    @Mock
    private lateinit var lastMatchVM: NextMatchVM


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastMatchVM = Mockito.mock(NextMatchVM::class.java)
    }

    @Test
    fun getDetailLeague() {
        val responseTeams = topResponse

        runBlocking {
            `when`(service.getNextMatch(match))
        }
            .thenReturn(Single.just(responseTeams))
        lastMatchVM.getNextMatch(match)


    }
}