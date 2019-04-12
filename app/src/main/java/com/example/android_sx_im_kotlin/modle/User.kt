package com.example.android_sx_im_kotlin.modle

import cn.bmob.v3.BmobObject

/**
 * Created by ZhangWenQiang on 2019/3/29
 * Description:
 */
class User(userName: String, pwd: String) : BmobObject() {

     var userName: String = userName
     var pwd: String = pwd

}
