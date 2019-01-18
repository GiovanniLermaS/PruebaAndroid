package com.co.pruebaandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.co.pruebaandroid.Connection.ResultService
import com.co.pruebaandroid.Connection.requestService
import com.co.pruebaandroid.Models.Movie

class MainActivity : AppCompatActivity(), ResultService {

    private val withNoFilter = "withNoFilter"

    private val withFilter = "withFilter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestService(null, withNoFilter)
    }

    override fun onDataReturn(result: Movie?, tag: String?) {
        when (tag) {
            withNoFilter -> {

            }
            withFilter -> {

            }
            else -> return
        }
    }

    override fun onFailedResponse(tag: String?) {

    }
}
