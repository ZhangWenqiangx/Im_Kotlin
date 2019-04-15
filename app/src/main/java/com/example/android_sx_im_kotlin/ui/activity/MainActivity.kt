package com.example.android_sx_im_kotlin.ui.activity

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

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
    }
}
