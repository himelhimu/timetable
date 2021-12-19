package com.example.sabbir.flixtime.di

import com.example.sabbir.flixtime.BuildConfig
import com.example.sabbir.flixtime.models.TimeTable
import com.example.sabbir.flixtime.network.DepartureService
import com.example.sabbir.flixtime.utils.DepartureJSONDeserilizer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by himelhimu on 12/17/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

const val BASE_URL = "https://global.api-dev.flixbus.com"

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {

        // only for development purposes
        // should not be added in prod
        /*val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY*/

        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            val request = builder.header("X-Api-Authentication", BuildConfig.API_TOKEN)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): DepartureService {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(TimeTable::class.java, DepartureJSONDeserilizer())
        val gson = gsonBuilder.create()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(DepartureService::class.java)
    }

}