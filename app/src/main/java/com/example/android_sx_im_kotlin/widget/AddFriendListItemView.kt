package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.modle.AddFriendItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){

    init {
        View.inflate(context!!, R.layout.view_add_friend_item,this)
    }

    fun bindView(addFriendItem: AddFriendItem) {
        userName.text = addFriendItem.name
        timestamp.text = addFriendItem.tiemstamp
        if(addFriendItem.isAdded){
            add.isEnabled = false
            add.text = context.getString(R.string.already_added)
        }else{
            add.isEnabled = true
            add.text = context.getString(R.string.add)
        }
        add.setOnClickListener { addFriend(addFriendItem.name) }
    }

    private fun addFriend(name: String) {
        try {
            EMClient.getInstance().contactManager().addContact(name,"just want to learn more about u")
            context.runOnUiThread { toast(R.string.send_message_success) }
        } catch (e: HyphenateException) {
            context.runOnUiThread { toast(e.message.toString()) }
        }
    }


}