package com.example.android_sx_im_kotlin.ui.activity

import android.view.KeyEvent
import android.widget.TextView
import com.example.android_sx_chat_kotlin.presenter.LoginPresenter
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by ZhangWenQiang on 2019/4/11
 * Description:
 */

class LoginActivity : BaseActivity(),LoginContract.View {

    private val presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        login.setOnClickListener { login() }                                            //监听点击事件
        password.setOnEditorActionListener(object : TextView.OnEditorActionListener{        //匿名内部类需要声明
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                login()
                return true
            }
        })
    }

    fun login(){
        hideSoftKeyboard()
        var userName = userName.text.trim().toString()                  //拿取控件中的属性
        var pwd = password.text.trim().toString()
        presenter.login(userName,pwd)
    }

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() =
        showProgressDialog(getString(R.string.logging))

    override fun onLoggedInSuccess() {
        dismissProgressDialog()
        startActivity<MainActivity>()
        finish()
    }

    override fun onLoggedInFailed(error: String?) {
        dismissProgressDialog()
        toast(R.string.login_failed.toString()+":"+error)
    }

    override fun getLayoutResId(): Int = R.layout.activity_login

}