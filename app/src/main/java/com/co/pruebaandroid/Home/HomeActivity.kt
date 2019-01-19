package com.co.pruebaandroid.Home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.co.pruebaandroid.Connection.*
import com.co.pruebaandroid.DescriptionItem.DescriptionItemActivity
import com.co.pruebaandroid.Login.LoginActivity
import com.co.pruebaandroid.Models.Movie
import com.co.pruebaandroid.Models.Result
import com.co.pruebaandroid.R
import com.co.pruebaandroid.Utils.Utils
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_image.view.*
import kotlinx.android.synthetic.main.view_toolbar.*

class HomeActivity : AppCompatActivity(), ResultService, OnClickItem, View.OnClickListener {

    private val withNoFilter = "withNoFilter"

    private val withFilter = "withFilter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isLogIn().count > 0) {
            ivLogOut.visibility = View.VISIBLE
        }
        requestService(null, withNoFilter)
        ivLogOut.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            ivLogOut -> {
                val movie = isLogIn()
                if (movie.moveToFirst()) {
                    val email = movie.getString(movie.getColumnIndex(Utils().EMAIL))
                    updateUser(email, "false")
                    ivLogOut.visibility = View.GONE
                    ivFavoriteToolbar.visibility = View.GONE
                }
            }
        }
    }

    override fun onClickImage(result: Result) {
        if (selectedMovie(result.id.toString()).count <= 0) {
            val id = if (result.id == null) ""
            else result.id.toString()
            val image = if (result.image?.small_url == null) "No imagen"
            else result.image.small_url.toString()
            val description = if (result.description == null) "No descripción"
            else result.description.toString()
            registerMovie(
                id,
                image,
                description,
                "false"
            )
        }
        if (isLogIn().count > 0) {
            val intent = Intent(this, DescriptionItemActivity::class.java)
            intent.putExtra("id", result.id.toString())
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", result.id.toString())
            startActivity(intent)
        }
    }

    override fun onDataReturn(result: Movie?, tag: String?) {
        when (tag) {
            withNoFilter -> {
                val listResults = result?.results
                val adp = GroupAdapter<ViewHolder>()
                for (list in listResults!!) adp.add(Images(list, this))
                rvImages.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
                rvImages.adapter = adp
            }
            withFilter -> {

            }
            else -> return
        }
    }

    override fun onFailedResponse(tag: String?) {

    }

    class Images(private val result: Result?, private val delegate: OnClickItem) : Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            Picasso.get().load(result?.image?.small_url)
                .into(viewHolder.itemView.ivCoverPageImage)
            viewHolder.itemView.setOnClickListener { delegate.onClickImage(result!!) }
        }

        override fun getLayout() = R.layout.view_image
    }
}

