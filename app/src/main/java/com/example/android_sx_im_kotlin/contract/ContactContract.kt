package com.example.android_sx_im_kotlin.contract

import com.example.android_sx_chat_kotlin.contract.BasePresenter

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description:
 */
interface ContactContract{
    interface Presenter: BasePresenter{
        fun loadContacts()
    }
    interface View{
        fun onLoadContacts(isSuccess: Boolean,error: String)
    }
}