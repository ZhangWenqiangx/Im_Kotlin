package com.example.android_sx_chat_kotlin.contract

import android.os.Handler
import android.os.Looper

/**
 * Created by ZhangWenQiang on 2019/4/11
 * Description:
 */
interface BasePresenter{

    companion object {                              //伴生对象 类似于java中的静态属性
        val handler by lazy {
            Handler(Looper.getMainLooper())         //声明在主线程
        }
    }

    fun uiThread(f: () -> Unit) =                    //将函数作为形参传入 并在handler中运行
        handler.post{f()}

}