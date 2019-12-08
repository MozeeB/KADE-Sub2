package com.zeeb.footballmatchschedule.screen.detail.search

import com.zeeb.footballmatchschedule.data.mapper.SearchMapper
import com.zeeb.footballmatchschedule.data.model.SearchModel
import com.zeeb.footballmatchschedule.data.repository.SearchRepositoryImpl
import com.zeeb.footballmatchschedule.data.response.TopSearchResponse
import com.zeeb.footballmatchschedule.data.service.GlobalService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchRepositoryImplTest{

    private val globalService = Mockito.mock(GlobalService::class.java)
    private val searchMatchMapper = Mockito.mock(SearchMapper::class.java)

    private lateinit var searchRepositoryImpl: SearchRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchRepositoryImpl = SearchRepositoryImpl(globalService, searchMatchMapper)
    }

    @Test
    fun searchMatch() {
            `when`(globalService.searchMatch("madrid")).thenReturn(Single.just(
                TopSearchResponse(
                    listOf(
                        SearchModel(
                            "671085",
                            "Real Madrid Baloncesto vs BC Zenit Saint Petersburg",
                            "",
                            "",
                            "",
                            "Real Madrid Baloncesto",
                            "BC Zenit Saint Petersburg",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "9:00:00"
                        )
                    )
                )
            ))

        searchRepositoryImpl.searchEvent("madrid")
            .test()
            .assertComplete()
    }
}