package com.example.android_sx_im_kotlin.contract

import com.example.android_sx_chat_kotlin.contract.BasePresenter

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
interface ChatContact{
    interface Presenter: BasePresenter{
        fun sendMessage(contact: String,msg: String)
    }
    interface View{
        fun onStartSendMessage()
        fun onSendMessageState(isSuccess: Boolean)
    }
}