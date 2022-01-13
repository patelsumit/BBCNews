package com.app.bbcnewsapp.models

import java.io.Serializable

data class NewsList(
    val headlines: ArrayList<Headline>
) : Serializable
data class Headline(
    val headline: String,
    val introduction: String,
    val updated: Int
) : Serializable