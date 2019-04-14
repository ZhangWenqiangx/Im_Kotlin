package com.example.android_sx_im_kotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.android_sx_im_kotlin.widget.ContactListItemView

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description:
 */

class ContactListAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder =
         ContactListItemViewHolder(ContactListItemView(context))

    override fun getItemCount(): Int = 30

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ContactListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {

    }
}