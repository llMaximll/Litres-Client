package com.github.llmaximll.litres_client.model.module

import android.content.Context
import com.github.llmaximll.litres_client.model.DataStore
import com.github.llmaximll.litres_client.model.EncryptedSharedPreferences
import com.github.llmaximll.litres_client.model.service.AuthService
import com.github.llmaximll.litres_client.model.service.remote_source.auth.AuthRemoteSource
import com.github.llmaximll.litres_client.model.service.remote_source.auth.AuthRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthStore(@ApplicationContext context: Context): DataStore = DataStore(context)

    @Provides
    @Singleton
    fun providesEncryptedSharedPrefs(@ApplicationContext context: Context): EncryptedSharedPreferences =
        EncryptedSharedPreferences(context)

    @Provides
    fun providesBaseUrl(): String = "https://catalit.litres.ru/"

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String
    ): Retrofit {
        val okHttpBuilder = OkHttpClient().newBuilder()

        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        okHttpBuilder.addInterceptor(interceptor)

        okHttpBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }

        val okHttpClient = okHttpBuilder.build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideAuthRemoteSource(impl: AuthRemoteSourceImpl): AuthRemoteSource {
        return impl
    }
}