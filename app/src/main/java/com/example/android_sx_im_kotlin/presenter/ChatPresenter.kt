package com.example.android_sx_im_kotlin.presenter

import com.example.android_sx_chat_kotlin.ui.adapter.EMCallBackAdapter
import com.example.android_sx_im_kotlin.contract.ChatContact
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
class ChatPresenter(val view: ChatContact.View): ChatContact.Presenter{

    val messages = mutableListOf<EMMessage>()

    companion object {
        const val PAGE_SIZE = 10
    }

    override fun sendMessage(contact: String, msg: String) {
        val emMessage = EMMessage.createTxtSendMessage(msg,contact)

        emMessage.setMessageStatusCallback(object : EMCallBackAdapter(){            //环信大部分回调在子线程 比目鱼回调都在主线程
            override fun onSuccess() {
                uiThread { view.onSendMessageState(true,"")}
            }

            override fun onError(code: Int, error: String?) {
                uiThread { view.onSendMessageState(false,error.toString()) }
            }
        })
        view.onStartSendMessage()
        messages.add(emMessage)
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addMessage(username: String, p0: MutableList<EMMessage>?) {
        p0?.let { messages.addAll(it) }
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }

    override fun loadMoreMessage(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val newMessages = conversation.loadMoreMsgFromDB(messages[0].msgId, PAGE_SIZE)
            messages.addAll(0,newMessages)
            uiThread {
                view.onMoreMessageLoad(newMessages.size)
            }
        }

    }

    override fun loadMessage(username: String) {
        doAsync {
            val conversatoin = EMClient.getInstance().chatManager().getConversation(username)
            messages.addAll(conversatoin.allMessages)
            uiThread {
                view.onLoadAllConversation()
            }
        }
    }
}