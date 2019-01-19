package com.co.pruebaandroid.Register

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.co.pruebaandroid.Connection.isExists
import com.co.pruebaandroid.Connection.registerUser
import com.co.pruebaandroid.DescriptionItem.DescriptionItemActivity
import com.co.pruebaandroid.Login.LoginActivity
import com.co.pruebaandroid.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btRegister.setOnClickListener(this)
        id = intent.getStringExtra("id")
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btRegister -> {
                if (isExists(
                        etEmailRegister.text.toString(),
                        etPasswordRegister.text.toString()
                    ).count > 0
                ) {
                    val toast =
                        Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                } else {
                    registerUser(
                        etNameRegister.text.toString(),
                        etEmailRegister.text.toString(),
                        etPasswordRegister.text.toString()
                    )
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            }
        }
    }
}