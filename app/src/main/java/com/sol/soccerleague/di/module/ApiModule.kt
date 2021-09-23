package com.sol.soccerleague.di.module

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sol.soccerleague.api.ApiConstant.API_KEY
import com.sol.soccerleague.api.ApiConstant.BASE_URL
import com.sol.soccerleague.api.ApiRepositoryImpl
import com.sol.soccerleague.api.ApiService
import com.sol.soccerleague.api.isInternetAvailable
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule(_application: Application) {
    private var application = _application

    @Provides
    @Singleton
    fun provideHttpCache(): Cache {
        val cacheSize:Long = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor { chain ->
                var request: Request = chain.request()
                if (!isInternetAvailable(application.applicationContext)) {
                    val maxStale = 300
                    request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .removeHeader("Pragma")
                        .build()
                }
                chain.proceed(request)
            }
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                val maxAge = 60
                response.newBuilder()
                    .header("X-Auth-Token", API_KEY)
                    .build()
            }
        return client.build()
    }

    @Provides
    @Reusable
    internal fun provideRetrofit(okHttpClient: OkHttpClient):
            Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Reusable
    internal fun provideApi(retrofit: Retrofit):
            ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Reusable
    internal fun provideRepositoryImpl(api: ApiService):
            ApiRepositoryImpl = ApiRepositoryImpl(api)
}