package com.co.pruebaandroid.SQL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.co.pruebaandroid.Utils.Utils

class ConnectionSqlHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Utils().CREATE_TABLE_USER)
        db?.execSQL(Utils().CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS    users")
        onCreate(db)
    }

//    fun Context.registerUser(name: String, email: String, password: String) {
//        val conn = ConnectionSqlHelper(this, "db_users", null, 1)
//        val db = conn.writableDatabase
//        val values = ContentValues()
//        values.put(Utils().NAME, name)
//        values.put(Utils().EMAIL, email)
//        values.put(Utils().PASSWORD, password)
//        db.insert(Utils().TABLE_USER, Utils().EMAIL, values)
//        db.close()
//    }
//
//    fun Context.getUser(email: String, password: String): Cursor {
//        val conn = ConnectionSqlHelper(this, "db_users", null, 1)
//        val db = conn.readableDatabase
//        return db.rawQuery(
//            "SELECT * FROM ${Utils().TABLE_USER} WHERE ${Utils().EMAIL} = " + email + " AND ${Utils().PASSWORD} = " + password,
//            null
//        )
//    }
}