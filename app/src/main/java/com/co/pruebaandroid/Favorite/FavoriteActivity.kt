package com.co.pruebaandroid.Favorite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.co.pruebaandroid.Connection.selectAllMovies
import com.co.pruebaandroid.Models.Favorite
import com.co.pruebaandroid.R
import com.co.pruebaandroid.Utils.Utils
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_description_item.view.*
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val listMovies = selectAllMovies()
        val listD = ArrayList<Favorite>()
        while (listMovies.moveToFirst()) {
            if (listMovies.count > 0) {
                val favorite = Favorite()
                favorite.image = listMovies.getString(listMovies.getColumnIndex(Utils().IMAGE))
                favorite.description = listMovies.getString(listMovies.getColumnIndex(Utils().DESCRIPTION))
                listD.add(favorite)
            } else {
                val toast = Toast.makeText(this, "No hay favoritos", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
                break
            }
        }
        val adp = GroupAdapter<ViewHolder>()
        for (list in listD) adp.add(FavoriteAdapter(list))
        rvFavorite.adapter = adp
    }

    class FavoriteAdapter(private val favorite: Favorite?) : Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(favorite?.image)
                .into(viewHolder.itemView.ivImageDescription)
            viewHolder.itemView.tvDescription.text = favorite?.description
            viewHolder.itemView.ivFavorite.visibility = View.GONE
        }

        override fun getLayout() = R.layout.activity_description_item
    }
}