package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.android_sx_im_kotlin.R
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
class ConversationListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){

    fun bindView(conversation: EMConversation) {
        userName.text = conversation.conversationId()                                   //name
        if(conversation.lastMessage.type == EMMessage.Type.TXT){                        //content
            val body = (conversation.lastMessage.body) as EMTextMessageBody
            lastMessage.text = body.message
        }else lastMessage.text = context.getString(R.string.no_text_message)

        val timestampString = DateUtils.getTimestampString(Date(conversation.lastMessage.msgTime))  //时间戳
        timestamp.text = timestampString

        if(conversation.unreadMsgCount > 0){                                            //红色角标
            unreadCount.visibility = View.VISIBLE
            unreadCount.text = conversation.unreadMsgCount.toString()
        }else{
            unreadCount.visibility = View.GONE
        }

    }

    init {
        View.inflate(context, R.layout.view_conversation_item,this)
    }

}