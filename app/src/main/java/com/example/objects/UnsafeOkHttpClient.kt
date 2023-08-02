package com.example.pharmaesi.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class UnsafeOkHttpClient {
    companion object{
        fun createOkHttpClient():OkHttpClient{
            var builder=OkHttpClient.Builder()
            var logginInterceptor= HttpLoggingInterceptor()
            logginInterceptor.level=HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logginInterceptor)
            return builder.build()


        }
    }
}