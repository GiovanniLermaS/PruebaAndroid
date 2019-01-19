package com.co.pruebaandroid.Connection

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.co.pruebaandroid.Models.Movie
import com.co.pruebaandroid.SQL.ConnectionSqlHelper
import com.co.pruebaandroid.Utils.Constants
import com.co.pruebaandroid.Utils.Utils
import com.co.pruebaandroid.Utils.getAPIService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ClientNet {
    companion object {
        private lateinit var retrofit: Retrofit
        private var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getClient(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.Connection.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
        }
    }
}

fun Context.isExists(email: String, password: String): Cursor {
    val conn = ConnectionSqlHelper(this, "db_users", null, 1)
    val db = conn.readableDatabase
    return db.rawQuery(
        "SELECT * FROM ${Utils().TABLE_USER} WHERE ${Utils().EMAIL} = '$email' AND ${Utils().PASSWORD} = '$password'",
        null
    )
}

fun Context.selectedMovie(id: String): Cursor {
    val conn = ConnectionSqlHelper(this, "db_users", null, 1)
    val db = conn.writableDatabase
    return db.rawQuery(
        "SELECT ${Utils().IMAGE}, ${Utils().DESCRIPTION}, ${Utils().IS_FAVORITE} FROM ${Utils().TABLE_MOVIE} WHERE ${Utils().ID} = '$id'",
        null
    )
}

fun Context.isLogIn(): Cursor {
    val conn = ConnectionSqlHelper(this, "db_users", null, 1)
    val db = conn.readableDatabase
    return db.rawQuery(
        "SELECT * FROM ${Utils().TABLE_USER} WHERE ${Utils().IS_LOGIN} = 'true'",
        null
    )
}

fun Context.registerUser(name: String, email: String, password: String) {
    val conn = ConnectionSqlHelper(this, "db_users", null, 1)
    val db = conn.writableDatabase
    val values = ContentValues()
    values.put(Utils().NAME, name)
    values.put(Utils().EMAIL, email)
    values.put(Utils().PASSWORD, password)
    db.insert(Utils().TABLE_USER, Utils().EMAIL, values)
    db.close()
}

fun Context.updateUser(email: String) {
    val conn = ConnectionSqlHelper(this, "db_users", null, 1)
    val db = conn.writableDatabase
    val values = ContentValues()
    values.put(Utils().EMAIL, email)
    values.put(Utils().IS_LOGIN, "true")
    db.update(Utils().TABLE_USER, values, "${Utils().EMAIL} ='$email'", null)
    db.close()
}

fun Context.registerMovie(id: String, image: String, description: String, isFavorite: String) {
    val conn = ConnectionSqlHelper(this, "db_users", null, 1)
    val db = conn.writableDatabase
    val values = ContentValues()
    values.put(Utils().ID, id)
    values.put(Utils().IMAGE, image)
    values.put(Utils().DESCRIPTION, description)
    values.put(Utils().IS_FAVORITE, isFavorite)
    db.insert(Utils().TABLE_MOVIE, Utils().ID, values)
    db.close()
}

fun Context.requestService(
    id: Int?,
    tag: String?
) {
    try {
        val listener = this@requestService as? ResultService
//        if (!this@requestService.isNetworkAvailable()) {
//            val retrofit =
//                ResponseRetrofit(0, "Lo sentimos movuu no encontro conexión a internet, intenta nuevamente.", null)
//            if (isProgress) this@requestService.closeProgress()
//            listener?.onFailedResponse(retrofit, tag)
//        }

        //if (isProgress) this@requestService.showProgress()
        val callback: Subscriber<Movie> = object : Subscriber<Movie>() {
            override fun onNext(t: Movie) {
                listener?.onDataReturn(t, tag)
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                print(e?.localizedMessage)
            }
        }
        if (id == null) {
            getAPIService().requestServiceGet("8e40387f0f6574ead164895af20a59a0c8e6e43b", "json", null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
        } else {
            getAPIService().requestServiceGet("8e40387f0f6574ead164895af20a59a0c8e6e43b", "json", "id:$id")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback)
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

}