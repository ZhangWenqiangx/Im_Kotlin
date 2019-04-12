package com.example.android_sx_im_kotlin.presenter

import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.android_sx_im_kotlin.contract.RegisterContract
import com.example.android_sx_im_kotlin.modle.User
import com.example.android_sx_im_kotlin.utils.isvalidPassword
import com.example.android_sx_im_kotlin.utils.isvalidUserName
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */

class RegisterPresenter(val view: RegisterContract.View): RegisterContract.Presenter{

    override fun register(userName: String, password: String, confirmPassword: String) {
        if(userName.isvalidUserName()){
            //检查密码
            if(password.isvalidPassword()){
                //检查确认密码
                if(password.equals(confirmPassword)){
                    //密码和确认密码一致
                    view.onStartRegister()
                    //开始注册
                    registerBmob(userName, password)
                } else view.onConfirmPasswordError()
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        val user = User(userName,password)
        user.save(object : SaveListener<String>() {
            override fun done(s: String?, ex: BmobException?) {
                if(ex == null){
                    registerEaseMob(userName, password,ex.toString())
                }else{
                    if(ex.errorCode == 202) view.onUserExist()
                    else view.onRegisterFailed(ex.toString())
                }
            }
        })
    }

    private fun registerEaseMob(userName: String, password: String,error: String) {
        doAsync {
            try {
                //注册失败会抛出HyphenateException
                EMClient.getInstance().createAccount(userName, password)//同步方法
                //注册成功
                uiThread { view.onRegisterSuccess() }
            } catch (e: HyphenateException) {
                //注册失败
                uiThread { view.onRegisterFailed(error) }
            }
        }
    }

}