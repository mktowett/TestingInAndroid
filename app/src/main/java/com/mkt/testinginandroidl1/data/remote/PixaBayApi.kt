package com.mkt.testinginandroidl1.data.remote

import com.mkt.testinginandroidl1.BuildConfig
import com.mkt.testinginandroidl1.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApi {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = "13891604-ad2eb1aad9e918571c6d3b910"
    ): Response<ImageResponse>
}