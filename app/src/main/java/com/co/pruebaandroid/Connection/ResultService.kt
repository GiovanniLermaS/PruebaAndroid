package com.co.pruebaandroid.Connection

import com.co.pruebaandroid.Models.Movie

interface ResultService {
    fun onDataReturn(result: Movie?, tag: String?)
    fun onFailedResponse(tag: String?)
}