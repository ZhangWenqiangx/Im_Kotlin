package com.example.android_sx_im_kotlin.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.contract.ChatContact
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity: BaseActivity(),ChatContact.View {

    lateinit var username: String

    override fun getLayoutResId(): Int = R.layout.activity_chat

    override fun init() {
        super.init()
        initHeader()
        initEditText()
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener{finish()}

        //获取聊天的用户名
        username = intent.getStringExtra("username")
        val titleString = String.format(getString(R.string.chat_title), username)
        headerTitle.text = titleString

    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                //如果用户输入的文本长度大于0，发送按钮enable
                send.isEnabled = !p0.isNullOrEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        edit.setOnEditorActionListener { p0, p1, p2 ->
//            send()
            true
        }
    }

    override fun onStartSendMessage() {
        //通知recyclerview刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageState(isSuccess: Boolean) {
        if(isSuccess){
            recyclerView.adapter?.notifyDataSetChanged()
            toast(R.string.send_message_success)
            edit.text.clear()
        }else{
            toast(R.string.send_message_failed)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}
