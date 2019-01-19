package com.co.pruebaandroid.DescriptionItem

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.co.pruebaandroid.Connection.selectedMovie
import com.co.pruebaandroid.Connection.updateFavorite
import com.co.pruebaandroid.Home.HomeActivity
import com.co.pruebaandroid.R
import com.co.pruebaandroid.Utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description_item.*

class DescriptionItemActivity : AppCompatActivity(), View.OnClickListener {

    var isFavorite = false

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_item)
        id = intent.getStringExtra("id")
        val movie = selectedMovie(id!!)
        if (movie.moveToFirst()) {
            Picasso.get().load(movie.getString(movie.getColumnIndex(Utils().IMAGE))).into(ivImageDescription)
            tvDescription.text = movie.getString(movie.getColumnIndex(Utils().DESCRIPTION))
            isFavorite = if (movie.getString(movie.getColumnIndex(Utils().IS_FAVORITE)) == "true") {
                Picasso.get().load(R.drawable.ic_star).into(ivFavorite)
                true
            } else {
                Picasso.get().load(R.drawable.ic_star_empty).into(ivFavorite)
                false
            }
        }
        ivFavorite.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            ivFavorite -> {
                isFavorite = if (isFavorite) {
                    Picasso.get().load(R.drawable.ic_star_empty).into(ivFavorite)
                    updateFavorite(id!!, "true")
                    false
                } else {
                    Picasso.get().load(R.drawable.ic_star).into(ivFavorite)
                    updateFavorite(id!!, "false")
                    true
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}