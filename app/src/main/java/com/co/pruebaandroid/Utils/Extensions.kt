package com.co.pruebaandroid.Utils

import com.co.pruebaandroid.Connection.ClientNet

fun getAPIService(): ApiInterface {
    return ClientNet.getClient().create(ApiInterface::class.java)
}