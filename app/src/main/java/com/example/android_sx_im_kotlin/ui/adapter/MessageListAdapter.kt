package com.example.android_sx_im_kotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android_sx_im_kotlin.widget.ReceiveMessageItemView
import com.example.android_sx_im_kotlin.widget.SendMessageItemView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
class MessageListAdapter(val context: Context, private val messages: List<EMMessage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_SEND_MESSAGE = 0
        const val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if(messages[position].direct() == EMMessage.Direct.SEND)
            ITEM_TYPE_SEND_MESSAGE
        else
            ITEM_TYPE_RECEIVE_MESSAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return if(type == ITEM_TYPE_SEND_MESSAGE)
            SendMessageViewHolder(SendMessageItemView(context))
        else
            ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val showTimestamp = isShowTimestamp(position)
        if(getItemViewType(position)== ITEM_TYPE_SEND_MESSAGE){
            val itemView = holder.itemView as SendMessageItemView
            itemView.bindView(messages[position],showTimestamp)
        }else{
            val itemView = holder.itemView as ReceiveMessageItemView
            itemView.bindView(messages[position],showTimestamp)
        }
    }

    private fun isShowTimestamp(position: Int): Boolean {
        //如果是第一条消息或者和前一条消息间隔时间比较长
        var showTimestamp = true
        if (position > 0) {
            showTimestamp = !DateUtils.isCloseEnough(messages[position].msgTime, messages[position - 1].msgTime)
        }
        return showTimestamp
    }

    class SendMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    class ReceiveMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}