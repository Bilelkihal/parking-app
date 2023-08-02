package com.example.objects

import com.example.Mymodule.Endpoint
import com.example.Mymodule.url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

        val  endpoint: Endpoint by lazy {
            Retrofit.Builder().baseUrl("https://cb49-129-45-31-60.ngrok.io").addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(
                Endpoint::class.java
            )
        }

}