package com.example.android_sx_im_kotlin.modle.db

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:联系人的实体类
 */
data class Contact(val map: MutableMap<String,Any>){
    val _id by map      //属性委托于map
    val name by map
}