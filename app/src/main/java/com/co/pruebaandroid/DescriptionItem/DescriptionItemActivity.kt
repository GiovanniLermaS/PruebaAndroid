package com.co.pruebaandroid.DescriptionItem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.co.pruebaandroid.Connection.selectedMovie
import com.co.pruebaandroid.R
import com.co.pruebaandroid.Utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description_item.*

class DescriptionItemActivity : AppCompatActivity(), View.OnClickListener {

    var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_item)
        val movie = selectedMovie(intent.getStringExtra("id"))
        Picasso.get().load(movie.getString(movie.getColumnIndex(Utils().IMAGE))).into(ivImageDescription)
        tvDescription.text = movie.getString(movie.getColumnIndex(Utils().DESCRIPTION))
        isFavorite = if (movie.getString(movie.getColumnIndex(Utils().IS_FAVORITE)) == "true") {
            Picasso.get().load(R.drawable.ic_star).into(ivFavorite)
            true
        } else {
            Picasso.get().load(R.drawable.ic_star_empty).into(ivFavorite)
            false
        }
        ivFavorite.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            ivFavorite -> {
                if (isFavorite)
                    Picasso.get().load(R.drawable.ic_star_empty).into(ivFavorite)
                else Picasso.get().load(R.drawable.ic_star).into(ivFavorite)
            }
        }
    }
}