package com.example.android_sx_im_kotlin.modle.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.android_sx_chat_kotlin.app.ImApplication
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.dropTable

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description: 存储联系人
 */
class DatabaseHelper(ctx: Context = ImApplication.instence) :
    ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object {
        const val NAME = "im.db"
        const val VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createSql = "CREATE TABLE IF NOT EXISTS ${ContactTable.NAME}("+
                "_id INTEGER PRIMARY_KEY AUTOINCREMENT NOT NULL,"+
                "name VARCHAR NOT NULL);"
//        p0?.createTable(ContactTable.NAME, true,
//            ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
//            ContactTable.CONTACT to TEXT)
        p0!!.execSQL(createSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME,true)
        onCreate(db)
    }
}