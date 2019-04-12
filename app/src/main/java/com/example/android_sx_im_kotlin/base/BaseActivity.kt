package com.example.android_sx_im_kotlin.base

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by ZhangWenQiang on 2019/4/11
 * Description:
 */

abstract class BaseActivity : AppCompatActivity() {

    private val progressDialog by lazy{
        ProgressDialog(this)
    }

    val inputManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        initStateBar()
        init()
    }

    private fun initStateBar() {
        var option: Int = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        if(Build.VERSION.SDK_INT > 21){
            window.decorView.systemUiVisibility = option
            window.navigationBarColor = Color.TRANSPARENT
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    open fun init(){
        //子类可选择此方法完成 一些初始化的操作
    }

    abstract fun getLayoutResId(): Int          //填充布局

    fun showProgressDialog(message: String) {   //根据提示展示进度条
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgressDialog(){                //进度条消失
        if(progressDialog.isShowing)
        progressDialog.hide()
    }

    fun hideSoftKeyboard(){
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken,0)
    }
}