package com.co.pruebaandroid.Utils

class Utils {

    val TABLE_USER = "users"

    val TABLE_MOVIE = "movies"

    val NAME = "name"

    val EMAIL = "email"

    val PASSWORD = "password"

    val IS_LOGIN = "isLogin"

    val ID = "id"

    val IMAGE = "image"

    val DESCRIPTION = "description"

    val IS_FAVORITE = "isFavorite"

    val CREATE_TABLE_USER: String =
        "CREATE TABLE $TABLE_USER ($NAME TEXT, $EMAIL TEXT, $PASSWORD TEXT, $IS_LOGIN TEXT)"

    val CREATE_TABLE_MOVIE: String =
        "CREATE TABLE $TABLE_MOVIE ($ID TEXT, $IMAGE TEXT, $DESCRIPTION TEXT, $IS_FAVORITE TEXT)"
}