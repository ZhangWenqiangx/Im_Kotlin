package com.example.android_sx_im_kotlin.ui.activity

import android.graphics.Color
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.MenuItem
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.factory.FragmentFactory
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    lateinit var badge: Badge

    override fun init() {
        super.init()

        val defaultFragmen = FragmentFactory.instance.getFragment(R.id.tab_conversation)            //先默认显示conversation
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame,defaultFragmen!!,"").commit()

        bottomNaBar.setOnNavigationItemSelectedListener (object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                val transaction = supportFragmentManager.beginTransaction()         //每次切换

                transaction.hide(FragmentFactory.instance.getFragment(R.id.tab_conversation)!!)         //先隐藏所有的fragment
                transaction.hide(FragmentFactory.instance.getFragment(R.id.tabs_contacts)!!)
                transaction.hide(FragmentFactory.instance.getFragment(R.id.tabs_dynamic)!!)

                val fragment: Fragment = FragmentFactory.instance.getFragment(p0.itemId)!!              //做判断显示

                if(!fragment.isAdded){
                    transaction.add(R.id.fragment_frame,fragment,"")
                }
                transaction.show(fragment)
                transaction.commit()
                return true
            }
        })

        val childAt = bottomNaBar.getChildAt(0) as BottomNavigationMenuView
        badge = QBadgeView(this)
            .bindTarget(childAt.getChildAt(0))
             .setBadgeNumber(0)
             .setBadgeGravity(Gravity.END or Gravity.TOP)
             .setBadgeTextColor(Color.WHITE)
             .setBadgeBackgroundColor(Color.RED)

        EMClient.getInstance().addConnectionListener(object : EMConnectionListener {
            override fun onConnected() {

            }

            override fun onDisconnected(p0: Int) {
                if (p0 == EMError.USER_LOGIN_ANOTHER_DEVICE){
                    //发生多设备登陆，跳转到登陆界面
                    startActivity<LoginActivity>()
                    finish()
                }
            }

        })


    }

    override fun onResume() {
        super.onResume()
        updateUnreadMsgCount()
    }

    private fun updateUnreadMsgCount() {
        val messageCount = EMClient.getInstance().chatManager().unreadMessageCount
        if(messageCount>99){
            badge.badgeText = "99+"
        }else if(messageCount>0){
            badge.badgeText = messageCount.toString()
        }else{
            badge.hide(false)
        }
    }
}
