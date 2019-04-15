package com.example.android_sx_im_kotlin.modle.db

import com.example.android_sx_im_kotlin.utils.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
class IMDatabase{

    companion object {
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact: Contact){
        databaseHelper.use {
            //todo bug
            insert(ContactTable.NAME, *contact.map.toVarargArray())
        }
    }

    fun getAllContacts(): List<Contact> = databaseHelper.use {
        select(ContactTable.NAME).parseList(object : MapRowParser<Contact> {
            override fun parseRow(columns: Map<String, Any>): Contact {
                return Contact(columns.toMutableMap())
            }
        })
    }

    fun deleteAllContacts(){
        databaseHelper.use {
            delete(ContactTable.NAME, null, null)
        }
    }
}