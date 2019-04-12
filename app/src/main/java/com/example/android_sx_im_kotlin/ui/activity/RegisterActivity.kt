package com.itheima.im.ui.activity

import android.view.KeyEvent
import android.widget.TextView
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.contract.RegisterContract
import com.example.android_sx_im_kotlin.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity: BaseActivity(),RegisterContract.View{

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register.setOnClickListener{register()}
        confirmPassword.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                register()
                return true
            }
        })
    }

    fun register(){
        hideSoftKeyboard()
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val confirmPasswordString = confirmPassword.text.trim().toString()
        presenter.register(userNameString, passwordString, confirmPasswordString)
    }

    override fun getLayoutResId(): Int = R.layout.activity_register

    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgressDialog(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dismissProgressDialog()
        finish()
    }

    override fun onRegisterFailed(error: String) {
        dismissProgressDialog()
        toast(getString(R.string.register_failed).toString()+":"+error)
    }

    override fun onUserExist() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}