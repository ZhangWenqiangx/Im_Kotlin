package com.example.android_sx_im_kotlin.presenter

import android.util.Log
import com.example.android_sx_im_kotlin.contract.ContactContract
import com.example.android_sx_im_kotlin.modle.ContactListItem
import com.example.android_sx_im_kotlin.modle.db.Contact
import com.example.android_sx_im_kotlin.modle.db.IMDatabase
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description:
 */
class ContactPresenter(val view:ContactContract.View): ContactContract.Presenter{

    val contactListItems = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            contactListItems.clear()
            IMDatabase.instance.deleteAllContacts()
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.sortBy { it[0] }                          //排序
                usernames.forEachIndexed { index, s ->                  //按照有下标的方式遍历
                    //是否显示首字母的提示 如果是第一条默认显示 或者当前条与前一条的首字母不一样
                    val showFirstLetter = index == 0 || s[0] != usernames[index-1][0]           /** 字符串可以用 == 判断 */
                    Log.d("99788",s)
                    val item = ContactListItem(s, s[0].toUpperCase(),showFirstLetter)           //用户名即username 首字母即为username 第0个字母
                    contactListItems.add(item)

                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)
                }
                uiThread { view.onLoadContacts(true,"") }
            } catch (e: HyphenateException) {
                uiThread { view.onLoadContacts(false,e.toString()) }
            }
        }
    }
}