package com.example.android_sx_chat_kotlin.presenter

import com.example.android_sx_chat_kotlin.contract.SplashContract
import com.hyphenate.chat.EMClient

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */

class SplashPresenter(val view: SplashContract.View):SplashContract.Presenter{

    //通过环信SDK检测是否登录 回调view层
    override fun checkLoginStatus() =
        if(isLogged())  view.checkLoggedIn(true) else view.checkLoggedIn(false)

    //调用modle层的判断登录状态  也就是环信sdk
    private fun isLogged(): Boolean =
        EMClient.getInstance().isLoggedInBefore && EMClient.getInstance().isConnected
}