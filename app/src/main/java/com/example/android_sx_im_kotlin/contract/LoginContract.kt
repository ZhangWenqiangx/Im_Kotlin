package com.example.android_sx_im_kotlin.contract

import com.example.android_sx_chat_kotlin.contract.BasePresenter

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */
interface LoginContract{

    interface Presenter : BasePresenter {
        fun login(username : String,password : String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed(error: String?)
    }
}