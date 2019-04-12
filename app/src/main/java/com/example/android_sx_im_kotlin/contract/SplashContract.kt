package com.example.android_sx_chat_kotlin.contract

/**
 * Created by ZhangWenQiang on 2019/4/11
 * Description:
 */

interface SplashContract{

    interface Presenter : BasePresenter{    //规范SplashPresenter层扩展
        fun checkLoginStatus()              //检查登录状态
    }

    interface View{
        fun checkLoggedIn(boolean: Boolean)       //判断登录与否 做相应view处理
    }

}