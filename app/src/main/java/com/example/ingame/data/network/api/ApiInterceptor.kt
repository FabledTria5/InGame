package com.example.ingame.data.network.api

import com.example.ingame.BuildConfig.GAMES_API_KEY
import okhttp3.Interceptor

object ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder().url(
            chain.request().url.newBuilder().addQueryParameter("key", GAMES_API_KEY).build()
        ).build()
    )
}