package com.example.android_sx_im_kotlin.contract

import com.example.android_sx_chat_kotlin.contract.BasePresenter

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
interface AddFriendContract{
    interface Presenter:BasePresenter{
        fun search(key: String)
    }

    interface View{
        fun onSearchAfter(isSuccess: Boolean,error: String)
    }
}