package com.kakao.membersapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection


class ApiClient {

    private lateinit var retrofit: Retrofit
    private var gson: Gson = GsonBuilder().create()

    companion object {
        var apiClient: ApiClient? = null

        fun getInstance(): ApiClient? {
            if(apiClient == null) {
                apiClient = ApiClient()
            }
            return apiClient
        }
    }
    fun getClient(): Retrofit {

        //hier konfigurieren wir den HTTP-Client(OkHttpClient), der von Retrofit genutzt wird
        val client2: OkHttpClient = OkHttpClient.Builder()

            .hostnameVerifier(HostnameVerifier { hostname, session ->
                val hv = HttpsURLConnection.getDefaultHostnameVerifier()
                true
            })
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()

        this.retrofit = Retrofit.Builder()
            .baseUrl(MembersAppKeys.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client2)
            .build()

        return retrofit
    }

}