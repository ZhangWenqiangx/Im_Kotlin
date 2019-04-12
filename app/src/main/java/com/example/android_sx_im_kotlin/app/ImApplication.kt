package com.example.android_sx_chat_kotlin.app

import android.app.Application
import cn.bmob.v3.Bmob
import com.hyphenate.chat.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */
class ImApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initHx()
        initBmob()
    }

    private fun initBmob() {
        Bmob.initialize(this, "e1cf23f46a232a95be6a9a93ed08dfc3")
    }

    private fun initHx() {
         val options =  EMOptions()
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.acceptInvitationAlways = false
        //初始化
        EMClient.getInstance().init(applicationContext, options)
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }
}
