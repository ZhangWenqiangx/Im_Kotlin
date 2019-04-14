package com.example.android_sx_im_kotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android_sx_im_kotlin.modle.ContactListItem
import com.example.android_sx_im_kotlin.widget.ContactListItemView

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description:
 */

class ContactListAdapter(
    private val context: Context,
    private val contactListItems: MutableList<ContactListItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder =
         ContactListItemViewHolder(ContactListItemView(context))

    override fun getItemCount(): Int = contactListItems.size

    override fun onBindViewHolder(holer: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holer.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
    }

    class ContactListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {

    }
}