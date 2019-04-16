package com.example.android_sx_im_kotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android_sx_im_kotlin.widget.ConversationListItemView
import com.hyphenate.chat.EMConversation

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
class ConversationListAdapter(
    val context: Context,
    private val conversations: MutableList<EMConversation>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

    }

    class ConversationListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}