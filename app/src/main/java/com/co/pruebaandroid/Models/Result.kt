package com.co.pruebaandroid.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Result : Serializable {

    @SerializedName("apiDetailURL")
    val apiDetailURL: String? = null
    @SerializedName("boxOfficeRevenue")
    val boxOfficeRevenue: String? = null
    @SerializedName("budget")
    val budget: String? = null
    @SerializedName("dateAdded")
    val dateAdded: String? = null
    @SerializedName("dateLastUpdated")
    val dateLastUpdated: String? = null
    @SerializedName("deck")
    val deck: String? = null
    @SerializedName("description")
    val description: String? = null
    @SerializedName("distributor")
    val distributor: Any? = null
    @SerializedName("hasStaffReview")
    val hasStaffReview: Any? = null
    @SerializedName("id")
    val id: Long? = null
    @SerializedName("image")
    val image: Image? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("producers")
    val producers = ArrayList<Producer>()
    @SerializedName("rating")
    val rating: String? = null
    @SerializedName("releaseDate")
    val releaseDate: String? = null
    @SerializedName("runtime")
    val runtime: String? = null
    @SerializedName("siteDetailURL")
    val siteDetailURL: String? = null
    @SerializedName("studios")
    val studios = ArrayList<Producer>()
    @SerializedName("totalRevenue")
    val totalRevenue: String? = null
    @SerializedName("writers")
    val writers = ArrayList<Producer>()
}