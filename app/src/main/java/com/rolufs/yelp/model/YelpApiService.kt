package com.rolufs.yelp.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rolufs.yelp.model.response.Business
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


const val API_KEY = "2LsnwAAsff_oIHz1LJasoX8-0AV6-hFWCYQziIYHQQVIbwHu9dMBODRaPDRMc80V8T_2VUmW5NKxTrQRUqOB7ANaAvAd1wuvg9lUfX81nh9tP2051BejH8KbjSYdXXYx"
const val url = "https://api.yelp.com/v3/"
const val yelpId = "zRlDhJgcwXEphTUhMaCfyw"

interface YelpApiService {

    @GET("businesses/{business}")
    fun getBusiness(@Path("business") business : String) : Deferred<Business>

    companion object {
        operator fun invoke(): YelpApiService {
            val requestInterceptor = Interceptor {chain ->

                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${API_KEY}")
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YelpApiService::class.java)
        }
    }
}