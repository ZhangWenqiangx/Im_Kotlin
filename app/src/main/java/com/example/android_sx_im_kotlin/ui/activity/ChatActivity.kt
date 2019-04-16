package com.example.android_sx_im_kotlin.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.base.BaseActivity
import com.example.android_sx_im_kotlin.contract.ChatContact
import com.example.android_sx_im_kotlin.presenter.ChatPresenter
import com.example.android_sx_im_kotlin.ui.adapter.MessageListAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.im.adapter.EMMessageListenerAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity: BaseActivity(),ChatContact.View {

    lateinit var username: String

    private val presenter = ChatPresenter(this)

    override fun getLayoutResId(): Int = R.layout.activity_chat

    private var messageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username,p0)
            runOnUiThread {
                recyclerView.adapter!!.notifyDataSetChanged()
                scrollToBottom()
            }
        }
    }

    override fun init() {
        super.init()
        initHeader()
        initEditText()
        initRecyclerView()
        send.setOnClickListener { send() }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        presenter.loadMessage(username)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context,presenter.messages)
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    //空闲时刻 检测
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){                         //监听滑动 判断是否需要刷新记录
                        val layoutManager = layoutManager as LinearLayoutManager
                        if(layoutManager.findFirstVisibleItemPosition() == 0){              //滑倒顶部
                            presenter.loadMoreMessage(username)
                        }
                    }
                }
            })
        }
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
            send()
            true
        }
    }

    private fun send(){
        hideSoftKeyboard()
        presenter.sendMessage(username,edit.text.trim().toString())
    }

    override fun onStartSendMessage() {
        //通知recyclerview刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageState(isSuccess: Boolean, error: String) {
        if(isSuccess){
            recyclerView.adapter?.notifyDataSetChanged()
            edit.text.clear()
            scrollToBottom()
        }else{
            toast(getString(R.string.send_message_failed)+error)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size-1)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

    override fun onLoadAllConversation() {
        recyclerView.adapter!!.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoad(size: Int) {
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)
    }

}
