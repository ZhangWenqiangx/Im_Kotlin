package com.example.android_sx_im_kotlin.ui.activity

import android.animation.ObjectAnimator
import android.os.Handler
import com.example.android_sx_chat_kotlin.contract.SplashContract
import com.example.android_sx_chat_kotlin.presenter.SplashPresenter
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

/**
 * Created by ZhangWenQiang on 2019/4/11
 * Description:
 */
class SplashActivity : BaseActivity(), SplashContract.View{

    private val presenter : SplashPresenter = SplashPresenter(this)

    /**
     *  const val 可见性为public final static，可以直接访问。避免频繁函数调用
     *  val 可见性为private final static，并且val 会生成方法getNormalObject()，通过方法调用访问。
     * */
    companion object {
        const val DELAY = 2000L                   //定义常量延迟
    }

    private val handler by lazy {           //委托懒加载 第一次运行之后不再运行
        Handler()
    }

    override fun init() {                       //覆写父级方法初始化时检测是否登录
        super.init()
        presenter.checkLoginStatus()
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash        //布局

    override fun checkLoggedIn(boolean: Boolean) {                       //View层实现
        if(boolean)                                                    //如果已经登录 那么就直接进入主页面
            startActivity<MainActivity>()
        else                                                         //如果没有登录 延迟2s跳转登录页面
            ObjectAnimator.ofFloat(logoShow, "alpha", 0f, 1f).setDuration(1500).start()
            handler.postDelayed({
                startActivity<LoginActivity>()
                    finish()
            }, DELAY)


    }

}