package com.co.pruebaandroid.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {

    @SerializedName("icon_url")
    val icon_url: String? = null
    @SerializedName("medium_url")
    val medium_url: String? = null
    @SerializedName("screen_url")
    val screen_url: String? = null
    @SerializedName("screen_large_url")
    val screen_large_url: String? = null
    @SerializedName("small_url")
    val small_url: String? = null
    @SerializedName("super_url")
    val super_url: String? = null
    @SerializedName("thumb_url")
    val thumb_url: String? = null
    @SerializedName("tiny_url")
    val tiny_url: String? = null
    @SerializedName("original_url")
    val original_url: String? = null
    @SerializedName("image_tags")
    val image_tags: String? = null
}