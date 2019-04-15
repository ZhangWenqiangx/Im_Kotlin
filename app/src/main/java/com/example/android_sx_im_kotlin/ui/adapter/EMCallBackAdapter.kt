package com.example.android_sx_chat_kotlin.ui.adapter

import com.hyphenate.EMCallBack

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */
open class EMCallBackAdapter: EMCallBack{
    override fun onSuccess() {
    }

    override fun onProgress(progress: Int, status: String?) {
    }

    override fun onError(code: Int, error: String?) {
    }

}