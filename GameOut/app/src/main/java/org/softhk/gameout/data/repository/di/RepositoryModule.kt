package org.softhk.gameout.data.repository.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.data.repository.remote.GameRepositoryAPIImpl
import org.softhk.gameout.data.repository.service.GameServiceAPI
import org.softhk.gameout.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providerOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                var request: Request = chain.request()
                    .newBuilder()
                    .addHeader("x-rapidapi-key","9de123bfebmsh0693793b9de5540p1adc0bjsn2c9fb0f481a9")
                    .build()
                return chain.proceed(request)
            }

        })
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providerGameServiceAPI (retrofit: Retrofit): GameServiceAPI {
        return retrofit.create(GameServiceAPI::class.java)
    }




    @Singleton
    @Provides
    fun providerGameRepositoryAPI(gameService: GameServiceAPI)
            : GameRepositoryAPI {
        return GameRepositoryAPIImpl(
            gameService
        )
    }
}