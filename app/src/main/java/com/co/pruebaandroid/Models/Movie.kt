package com.co.pruebaandroid.Models

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("error")
    val error: String? = null,
    @SerializedName("limit")
    val limit: Long? = null,
    @SerializedName("offset")
    val offset: Long? = null,
    @SerializedName("numberOfPageResults")
    val numberOfPageResults: Long? = null,
    @SerializedName("numberOfTotalResults")
    val numberOfTotalResults: Long? = null,
    @SerializedName("statusCode")
    val statusCode: Long? = null,
    @SerializedName("results")
    val results: ArrayList<Result> = ArrayList(),
    @SerializedName("version")
    val version: String? = null
)