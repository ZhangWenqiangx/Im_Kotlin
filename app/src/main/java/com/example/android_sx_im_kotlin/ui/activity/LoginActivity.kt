package com.example.android_sx_im_kotlin.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.view.KeyEvent
import android.widget.TextView
import com.example.android_sx_chat_kotlin.presenter.LoginPresenter
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.contract.LoginContract
import com.itheima.im.ui.activity.RegisterActivity
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

        newUser.setOnClickListener{startActivity<RegisterActivity>()}

        login.setOnClickListener { login() }                                            //监听点击事件
        password.setOnEditorActionListener(object : TextView.OnEditorActionListener{        //匿名内部类需要声明
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                login()
                return true
            }
        })
    }

    fun login(){
        hideSoftKeyboard()                      //隐藏软键盘

        if(hasWriteExternalStoragePermission()) {
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.login(userNameString, passwordString)
        } else applyWriteExteranlStoragePermissino()

        var userName = userName.text.trim().toString()                  //拿取控件中的属性
        var pwd = password.text.trim().toString()
        presenter.login(userName,pwd)
    }

    private fun applyWriteExteranlStoragePermissino() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    //检查是否有写磁盘的权限
    private fun hasWriteExternalStoragePermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //用户同意权限,开始登陆
            login()
        } else toast(R.string.permission_denied)
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