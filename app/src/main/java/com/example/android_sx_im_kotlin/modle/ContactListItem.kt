package com.example.android_sx_im_kotlin.modle

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description: 包装一个关于list中item项 用来携带数据
 */
data class ContactListItem(val username: String,val firstLetter: Char,val showForstLetter: Boolean = true)