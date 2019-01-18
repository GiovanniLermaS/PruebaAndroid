package com.co.pruebaandroid.Login

import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.co.pruebaandroid.R
import com.co.pruebaandroid.SQL.ConnectionSqlHelper
import com.co.pruebaandroid.Utils.Utils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun getUser(): Cursor {
        val conn = ConnectionSqlHelper(this, "db_users", null, 1)
        val db = conn.readableDatabase
        return db.rawQuery(
            "SELECT * FROM ${Utils().TABLE_USER} WHERE ${Utils().EMAIL} = " + etEmailLogIn.text.toString() + " AND ${Utils().PASSWORD} = " + etPasswordLogIn.text.toString(),
            null
        )
    }
}