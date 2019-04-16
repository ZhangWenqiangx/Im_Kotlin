package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.android_sx_im_kotlin.R

/**
 * Created by ZhangWenQiang on 2019/4/16
 * Description:
 */
class ConversationListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs){

    init {
        View.inflate(context, R.layout.view_conversation_item,this)
    }

}