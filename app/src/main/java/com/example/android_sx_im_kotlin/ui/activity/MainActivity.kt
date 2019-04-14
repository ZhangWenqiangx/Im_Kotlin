package com.example.android_sx_im_kotlin.ui.activity

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.MenuItem
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        bottomNaBar.setOnNavigationItemSelectedListener (object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {

                val transaction = supportFragmentManager.beginTransaction()
                val fragment: Fragment = FragmentFactory.instance.getFragment(p0.itemId)!!
                transaction.replace(R.id.fragment_frame,fragment)
                transaction.commit()
                return true
            }
        })
    }
}
