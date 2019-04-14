package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.android_sx_im_kotlin.R
import com.example.android_sx_im_kotlin.modle.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 * Created by ZhangWenQiang on 2019/4/14
 * Description:
 */
class ContactListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    fun bindView(contactListItem: ContactListItem) {
                                                                                    //当数据传来时的初始化
        if(contactListItem.showForstLetter){
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = contactListItem.firstLetter.toString()
        }else firstLetter.visibility = View.GONE

        userName.text = contactListItem.username
    }

    init {
        View.inflate(context, R.layout.view_contact_item,this)                  //自定义view的布局填充
    }

}