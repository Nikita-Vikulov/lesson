package com.example.lesson.data.di.modules

import android.content.Context
import com.example.lesson.remote.ApiHolder
import com.example.lesson.remote.GithubUsersService
import com.example.lesson.remote.IApiHolder
import com.example.lesson.utils.AndroidNetworkStatus
import com.example.lesson.utils.INetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
//
//    @Binds
//    fun abc(impl: ApiHolder): IApiHolder
//

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.github.com"

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun apiHolder(apiService: GithubUsersService): IApiHolder {
        return ApiHolder(apiService = apiService)
    }

    @Singleton
    @Provides
    fun githubUsersService(
        @Named("baseUrl") baseUrl: String,
        gson: Gson
    ): GithubUsersService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GithubUsersService::class.java)
    }

    @Provides
    @Singleton
    fun networkStatus(context: Context): INetworkStatus =
        AndroidNetworkStatus(context)
}

