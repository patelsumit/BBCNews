package com.app.bbcnewsapp.endpoint

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EndPointInstance {
    companion object {
        private val baseUrl =
            "https://raw.githubusercontent.com/bbc/news-and-weather-apps-coding-challenge-trainees/master/"
        fun getEndPointInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}