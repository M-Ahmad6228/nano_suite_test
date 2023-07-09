package com.test.nano_suite.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.nano_suite.activities.splash.Splash
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            Interceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                if (response.code() == 400 || response.code() == 403 || response.code() == 404 || response.code() == 500) {
                    return@Interceptor response;
                }
                response
            })
        .readTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .cache(null)
        .build()
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    val retrofitInstance: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(Splash.baseAPIURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
        }
}