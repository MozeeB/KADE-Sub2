package com.zeeb.footballmatchschedule.screen.detail

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



class DetailVMTest{

    private val league = "English Premier League"

    @Mock
    private lateinit var service: GlobalService

    @Mock
    private lateinit var topResponse: TopDetailResponse

    @Mock
    private lateinit var detailVM: DetailVM


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailVM = Mockito.mock(DetailVM::class.java)
    }

    @Test
    fun getDetailLeague() {
        val responseTeams = topResponse

        runBlocking {
            `when`(service.getDetailLiga(league))
        }
            .thenReturn(Single.just(responseTeams))
        detailVM.getDetailLiga(league)


    }
}