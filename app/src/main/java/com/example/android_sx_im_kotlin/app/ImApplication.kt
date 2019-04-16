package com.example.android_sx_chat_kotlin.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import cn.bmob.v3.Bmob
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.ui.activity.ChatActivity
import com.hyphenate.chat.*
import com.itheima.im.adapter.EMMessageListenerAdapter

/**
 * Created by ZhangWenQiang on 2019/4/12
 * Description:
 */
class ImApplication : Application() {

    val soundPool= SoundPool(2,AudioManager.STREAM_MUSIC,0)

    companion object {
        lateinit var instence: ImApplication
    }

    val duan by lazy {
        soundPool.load(instence, R.raw.duan,0)
    }

    val yulu by lazy {
        soundPool.load(instence, R.raw.yulu,0)
    }


    private val messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            //如果是在前台，则播放短的声音
            if(isForeground()){
                soundPool.play(duan,1f,1f,0,0,1f)
            }else{
                soundPool.play(yulu,1f,1f,0,0,1f)
                showNotification(p0)
            }
        }
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        p0?.forEach {
            var contentText = getString(R.string.no_text_message)
            if(it.type == EMMessage.Type.TXT)
                contentText = (it.body as EMTextMessageBody).message

            val intent = Intent(this,ChatActivity::class.java)
            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            val putExtra = intent.putExtra("username", it.msgId+"" )
            val notification = Notification.Builder(this)
                .setContentText(contentText)
                .setContentTitle(getString(R.string.receive_new_message))
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.avatar1))
                .setSmallIcon(R.mipmap.ic_contact)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .notification
            notificationManager.notify(1,notification)
        }

    }

    private fun isForeground(): Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses) {
            if (runningAppProcess.processName == packageName){
                //根据包名寻找进程 如果找到 就是在前台运行 否则 在后台
                return runningAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }

    override fun onCreate() {
        super.onCreate()

        instence = this

        initHx()
        initBmob()
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
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
