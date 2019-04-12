package com.example.android_sx_im_kotlin.contract

import com.example.android_sx_chat_kotlin.contract.BasePresenter

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */

interface RegisterContract{
    interface Presenter : BasePresenter{
        fun register(userName: String, password: String, confirmPassword: String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
        fun onUserExist()
    }
}