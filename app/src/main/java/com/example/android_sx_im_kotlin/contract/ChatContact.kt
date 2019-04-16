package com.example.android_sx_im_kotlin.contract

import com.example.android_sx_chat_kotlin.contract.BasePresenter
import com.hyphenate.chat.EMMessage

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
interface ChatContact{
    interface Presenter: BasePresenter{
        fun sendMessage(contact: String,msg: String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessage(username: String)
        fun loadMoreMessage(username: String)
    }
    interface View{
        fun onStartSendMessage()
        fun onSendMessageState(isSuccess: Boolean,error: String)
        fun onLoadAllConversation()
        fun onMoreMessageLoad(size: Int)
    }
}