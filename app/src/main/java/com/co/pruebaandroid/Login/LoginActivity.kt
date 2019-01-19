package com.co.pruebaandroid.Login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.co.pruebaandroid.Connection.isExists
import com.co.pruebaandroid.DescriptionItem.DescriptionItemActivity
import com.co.pruebaandroid.R
import com.co.pruebaandroid.Register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener(this)
        btRegisterLogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btLogin -> {
                if (isExists(etEmailLogIn.text.toString(), etPasswordLogIn.text.toString()).count > 0) {
                    val intent = Intent(this, DescriptionItemActivity::class.java)
                    intent.putExtra("id", intent.getStringExtra("id"))
                    startActivity(intent)
                } else {
                    val toast =
                        Toast.makeText(this, "El usuario no existe o la contraseña es incorrecta", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                }
            }
            btRegisterLogin -> {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("id", intent.getStringExtra("id"))
                startActivity(intent)
            }
        }
    }
}