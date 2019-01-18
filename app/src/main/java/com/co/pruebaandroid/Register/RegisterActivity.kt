package com.co.pruebaandroid.Register

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.co.pruebaandroid.R
import com.co.pruebaandroid.SQL.ConnectionSqlHelper
import com.co.pruebaandroid.Utils.Utils
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    private fun registerUsers() {
        val conn = ConnectionSqlHelper(this, "db_users", null, 1)
        val db = conn.writableDatabase
        val values = ContentValues()
        values.put(Utils().NAME, etName.text.toString())
        values.put(Utils().EMAIL, etEmail.text.toString())
        values.put(Utils().PASSWORD, etPassword.text.toString())
        db.insert(Utils().TABLE_USER, Utils().EMAIL, values)
        db.close()
    }
}