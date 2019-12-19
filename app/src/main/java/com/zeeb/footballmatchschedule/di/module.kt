package com.zeeb.footballmatchschedule.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.zeeb.footballmatchschedule.BuildConfig
import com.zeeb.footballmatchschedule.data.local.database.AppDatabase
import com.zeeb.footballmatchschedule.data.mapper.*
import com.zeeb.footballmatchschedule.data.repository.*
import com.zeeb.footballmatchschedule.data.service.GlobalInterceptor
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.screen.detail.DetailVM
import com.zeeb.footballmatchschedule.screen.detail.detailmatch.DetailMatchVM
import com.zeeb.footballmatchschedule.screen.detail.detailteam.DetailTeamVM
import com.zeeb.footballmatchschedule.screen.detail.lastmatch.LastMatchVM
import com.zeeb.footballmatchschedule.screen.detail.nextmatch.NextMatchVM
import com.zeeb.footballmatchschedule.screen.detail.search.pertandingan.SearchVM
import com.zeeb.footballmatchschedule.screen.detail.search.team.SearchTeamVM
import com.zeeb.footballmatchschedule.screen.detail.standing.StandingVM
import com.zeeb.footballmatchschedule.screen.detail.team.TeamVM
import com.zeeb.footballmatchschedule.screen.favorite.team.FavTeamVM
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

val appModule = module {

    single { GlobalInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<GlobalService>(get(), BuildConfig.URL_API) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "football.db"
        ).build()
    }

}

val dataModule = module {

    single { getExecutor() }

    single { get<AppDatabase>().teamDao() }

    //repository
    single { FootballDetailRepositoryImpl(get(), get()) as FootballDetailRepository }
    single { LastMatchRepositoryImpl(get(), get()) as LastMatchRepository }
    single { NextMatchRepositoryImpl(get(), get()) as NextMatchRepository }
    single { DetailMatchRepositoryImpl(get(), get(), get()) as DetailMatchRepository }
    single { SearchRepositoryImpl(get(), get()) as SearchRepository }
    single { StandingRepositoryImpl(get(), get()) as StandingRepository }
    single { TeamsRepositoryImpl(get(), get()) as TeamsRepository }

    //mapper
    single { FootballDetailMapper() }
    single { LastMatchMapper() }
    single { DetailMatchMapper() }
    single { SearchMapper() }
    single { TeamLogoMapper() }
    single { StandingMapper() }
    single { TeamsMapper() }

    //viewmodel
    viewModel { DetailVM(get()) }
    viewModel { LastMatchVM(get()) }
    viewModel { NextMatchVM(get()) }
    viewModel { DetailMatchVM(get()) }
    viewModel {
        SearchVM(
            get()
        )
    }
    viewModel { StandingVM(get()) }
    viewModel { TeamVM(get()) }
    viewModel { DetailTeamVM(get(), get(), get()) }
    viewModel { SearchTeamVM(get()) }
    viewModel { FavTeamVM(get()) }

}

fun createOkHttpClient(interceptor: GlobalInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//    val timeout = 60L
    return OkHttpClient.Builder()
//        .connectTimeout(timeout, TimeUnit.SECONDS)
//        .readTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()
}
fun getExecutor(): Executor {
    return Executors.newFixedThreadPool(2)
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

val myAppModule = listOf(appModule, dataModule)
