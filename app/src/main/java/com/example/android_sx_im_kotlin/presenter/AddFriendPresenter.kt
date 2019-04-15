package com.example.android_sx_im_kotlin.presenter

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.android_sx_im_kotlin.contract.AddFriendContract
import com.example.android_sx_im_kotlin.modle.AddFriendItem
import com.example.android_sx_im_kotlin.modle.User
import com.example.android_sx_im_kotlin.modle.db.IMDatabase
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */

class AddFriendPresenter(val view: AddFriendContract.View): AddFriendContract.Presenter{

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {
        val bmobQuery = BmobQuery<User>()                           //根据key创建查询
        bmobQuery.addWhereContains("userName",key)
            .addWhereNotEqualTo("userName",EMClient.getInstance().currentUser)

        bmobQuery.findObjects(object : FindListener<User>(){        //查询相关key的用户
            override fun done(p0: MutableList<User>?, p1: BmobException?) {     //同步
                if (p1 == null) {
                    val allContacts = IMDatabase.instance.getAllContacts()

                    doAsync {
                        p0?.forEach {                                   //异步查询

                            var isAdded = false
                            for (contact in allContacts){
                                if(contact.name == it.userName){
                                    isAdded = true
                                }
                            }

                            val addFriendItem = AddFriendItem(it.userName,it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread { view.onSearchAfter(true,"") }
                    }
                }
                else view.onSearchAfter(false,p1.toString())
            }
        })

    }

}