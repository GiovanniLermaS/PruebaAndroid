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
}