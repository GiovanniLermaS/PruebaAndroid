package com.co.pruebaandroid.Connection

import android.content.Context
import com.co.pruebaandroid.Models.Movie
import com.co.pruebaandroid.Utils.Constants
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