package com.co.pruebaandroid.Utils

import com.co.pruebaandroid.Models.Movie
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiInterface {

    @GET("movies/")
    fun requestServiceGet(
        @Query("api_key") api_key: String?,
        @Query("format") format: String?,
        @Query("filter") filter: String?
    ): Observable<Movie?>
}