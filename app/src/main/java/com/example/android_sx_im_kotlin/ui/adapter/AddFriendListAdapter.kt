package com.example.android_sx_im_kotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android_sx_im_kotlin.modle.AddFriendItem
import com.example.android_sx_im_kotlin.widget.AddFriendListItemView

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
class AddFriendListAdapter(
    val context: Context,
    private val addFriendItems: MutableList<AddFriendItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return AddFriendListViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = addFriendItems.size

    override fun onBindViewHolder(holer: RecyclerView.ViewHolder, position: Int) {
        val itemView = holer.itemView as AddFriendListItemView
        itemView.bindView(addFriendItems[position])
    }

    class AddFriendListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}