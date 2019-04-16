package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.android_sx_im_kotlin.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){
    fun bindView(emMessage: EMMessage,showTimestamp: Boolean) {

        if(emMessage.type == EMMessage.Type.TXT)                        //关于文字的设置
            receiveMessage.text = emMessage.body.toString()
        else
            receiveMessage.text = context.getString(R.string.no_text_message)

        if(showTimestamp){                                              //关于时间戳的设置
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else timestamp.visibility = View.GONE
    }

    init {
        View.inflate(context, R.layout.view_receive_message_item,this)
    }
}