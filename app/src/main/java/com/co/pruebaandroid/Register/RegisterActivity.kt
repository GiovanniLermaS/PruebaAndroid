package com.co.pruebaandroid.Register

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.co.pruebaandroid.Connection.registerUser
import com.co.pruebaandroid.DescriptionItem.DescriptionItemActivity
import com.co.pruebaandroid.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btRegister.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btRegister -> {
                registerUser(
                    etNameRegister.text.toString(),
                    etEmailRegister.text.toString(),
                    etPasswordRegister.text.toString()
                )
                val intent = Intent(this, DescriptionItemActivity::class.java)
                intent.putExtra("id", intent.getStringExtra("id"))
                startActivity(intent)
            }
        }
    }
}