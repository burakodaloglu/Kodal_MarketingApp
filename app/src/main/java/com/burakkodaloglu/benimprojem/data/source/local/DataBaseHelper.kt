package com.burakkodaloglu.benimprojem.data.source.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.burakkodaloglu.benimprojem.data.model.User

val database_name="VeriTabanim"
val table_name="Kullanicilar"
val col_eposta="eposta"
val col_sifre="sifre"
val col_id="id"

class DataBaseHelper(var context: Context): SQLiteOpenHelper(context, database_name,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable=" CREATE TABLE "+ table_name+"("+
                col_id +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                col_eposta +" VARCHAR(256),"+
                col_sifre +"  VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun insertData(kullanicilar: User) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col_eposta, kullanicilar.email)
        cv.put(col_sifre, kullanicilar.password)
        val sonuc = db.insert(table_name, null, cv)

        if (sonuc == (-1).toLong()) {
            Toast.makeText(context, "Hatalı", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Başarılı", Toast.LENGTH_LONG).show()
        }
    }
}