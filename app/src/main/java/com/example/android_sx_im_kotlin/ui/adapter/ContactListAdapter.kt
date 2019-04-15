package com.example.android_sx_im_kotlin.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android_sx_chat_kotlin.ui.adapter.EMCallBackAdapter
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.modle.ContactListItem
import com.example.android_sx_im_kotlin.ui.activity.ChatActivity
import com.example.android_sx_im_kotlin.widget.ContactListItemView
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.*

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description:
 */

class ContactListAdapter(
    private val context: Context,
    private val contactListItems: MutableList<ContactListItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder =
         ContactListItemViewHolder(ContactListItemView(context))

    override fun getItemCount(): Int = contactListItems.size

    override fun onBindViewHolder(holer: RecyclerView.ViewHolder, position: Int) {

        val contactListItemView = holer.itemView as ContactListItemView
        val username = contactListItems[position].username

        contactListItemView.bindView(contactListItems[position])

        contactListItemView.setOnClickListener{                     //点击跳转聊天页面
            context.startActivity<ChatActivity>( "username" to username)
        }

        contactListItemView.setOnLongClickListener {                //长按弹出dialog判断是否删除好友

            val message = String.format(context.getString(R.string.delete_friend_message), username)

            context.alert( message , R.string.delete_friend_title.toString()) {
                positiveButton("sure"){ deleteFriend(username) }
                negativeButton("cancle"){ }
            }.show()

            true
        }
    }

    private fun deleteFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncDeleteContact(userName, object : EMCallBackAdapter(){
            override fun onSuccess() =
                context.runOnUiThread { toast(R.string.delete_friend_success) }

            override fun onError(p0: Int, p1: String?) =
                context.runOnUiThread { toast(R.string.delete_friend_failed) }
        })
    }

    class ContactListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!)
}