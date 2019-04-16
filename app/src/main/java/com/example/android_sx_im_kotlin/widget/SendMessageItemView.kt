package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.android_sx_im_kotlin.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){
    private lateinit var animationDrawable: AnimationDrawable

    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {

        if(emMessage.type == EMMessage.Type.TXT)                        //关于文字的设置
            sendMessage.text = (emMessage.body as EMTextMessageBody).message
        else
            sendMessage.text = context.getString(R.string.no_text_message)

        if(showTimestamp){                                              //关于时间戳的设置
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else timestamp.visibility = View.GONE


        emMessage.status().let {                                        //关于信息进度条的设置
            when(it){
                EMMessage.Status.SUCCESS -> {
                    sendMessageProgress.visibility = View.GONE
                }
                EMMessage.Status.FAIL ->{
                    sendMessageProgress.visibility = View.GONE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
                //todo /** 关于动画内存的消耗问题 */
                EMMessage.Status.INPROGRESS ->{
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
            }
        }

    }

    init {
        View.inflate(context, R.layout.view_send_message_item,this)
    }
}