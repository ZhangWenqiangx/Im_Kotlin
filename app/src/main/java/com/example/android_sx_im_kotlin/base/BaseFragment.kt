package com.example.android_sx_chat_kotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ZhangWenQiang on 2019/4/11
 * Description:
 */
abstract class BaseFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(getLayoutResId(),container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    open fun init(){}

    abstract fun getLayoutResId(): Int

}