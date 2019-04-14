package com.example.android_sx_im_kotlin.utils

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description: 扩展工具类
 */
fun String.isvalidUserName(): Boolean = this.matches(Regex("^[a-zA-Z0-9]\\w{2,19}$"))
fun String.isvalidPassword(): Boolean = this.matches(Regex("^[0-9]{3,20}$"))

fun String.isvalidPasswordx(): Boolean{             //扩展String类 做一个校验的函数
    return this.matches(Regex(""))          //this 指当前对象
}