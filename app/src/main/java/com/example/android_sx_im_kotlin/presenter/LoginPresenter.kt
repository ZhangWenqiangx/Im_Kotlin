package com.example.android_sx_chat_kotlin.presenter

import com.example.android_sx_chat_kotlin.ui.adapter.EMCallBackAdapter
import com.example.android_sx_im_kotlin.contract.LoginContract
import com.example.android_sx_im_kotlin.utils.isvalidPassword
import com.example.android_sx_im_kotlin.utils.isvalidUserName
import com.hyphenate.chat.EMClient

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */
class LoginPresenter(private val view : LoginContract.View) : LoginContract.Presenter{
    override fun login(username : String,password : String) {

        //验证账号
        if(username.isvalidUserName()){
            //账号验证成功 开始验证密码
            if(password.isvalidPassword()){
                //密码验证成功 view层展示进度条 调用model 层 登录
                view.onStartLogin()
                startLoginEM(username,password)
            }else view.onPasswordError()
        }else view.onUserNameError()
    }

    private fun startLoginEM(username: String, password: String) {
        EMClient.getInstance().login(username,password,object : EMCallBackAdapter() {
            //子线程回调
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                //主线程通知view
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onError(code: Int, error: String?) {
                uiThread { view.onLoggedInFailed(error) }
            }

        })
    }

}