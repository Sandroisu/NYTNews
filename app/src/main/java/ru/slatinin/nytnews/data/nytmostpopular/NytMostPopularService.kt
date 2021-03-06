package ru.slatinin.nytnews.data.nytmostpopular

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.slatinin.nytnews.BuildConfig

interface NytMostPopularService {

    @GET("svc/mostpopular/v2/{type}/7.json")
    suspend fun loadNews(
        @Path ("type") type: String,
        @Query("api-key") clientId: String = BuildConfig.NEW_YORK_TIMES
    ): MostPopularResponse

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/"

        fun create(): NytMostPopularService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NytMostPopularService::class.java)
        }
    }
}