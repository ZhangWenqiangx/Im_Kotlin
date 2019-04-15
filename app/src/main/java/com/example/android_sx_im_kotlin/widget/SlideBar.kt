package com.example.android_sx_im_kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.android_sx_im_kotlin.R
import org.jetbrains.anko.sp

/**
 * Created by ZhangWenQiang on 2019/4/15
 * Description:
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null): View(context, attrs) {

    val paint = Paint()
    var sectionHeight = 0f
    var  textBaseLine = 0f
    var onSectionChangeListener: OnSectionChangeListener?  = null

    companion object {
        private val SECTIONS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L","M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        sectionHeight = h *1.0f / SECTIONS.size                     //每个项的高度

        val fontMetrics = paint.fontMetrics
        //ascent指项中字母的顶部到baseline的距离  descent指字母的底部到baseline的距离
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        //                矩形中线              中线到baseline的间距
        textBaseLine = sectionHeight / 2 + (textHeight / 2 - fontMetrics.descent)
        //                                      字符整体的高度一半 - decent
    }

    override fun onDraw(canvas: Canvas) {
        val x = width * 1.0f / 2
        var baseline = textBaseLine
        SECTIONS.forEach {
            canvas.drawText(it,x, baseline,paint)
            baseline += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN ->{                                         ///判断按下的事件
                setBackgroundResource(R.drawable.bg_slide_bar)

                val index = getTouchIndex(event)                          //设置监听抛给外界
                onSectionChangeListener?.onSectionChange(SECTIONS[index])
            }
            MotionEvent.ACTION_MOVE-> {
                val index = getTouchIndex(event)
                onSectionChangeListener?.onSectionChange(SECTIONS[index])
            }
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChangeListener?.onSlideFinish()
            }
        }

        return true
    }

    private fun getTouchIndex(event: MotionEvent):Int {

        var index = (event.y / sectionHeight).toInt()

        if(index<0){                            //越界判断
            index = 0
        }else if(index >= SECTIONS.size){
            index = SECTIONS.size-1
        }

        return index
    }

    interface OnSectionChangeListener{
        fun onSectionChange(firstLetter: String)
        fun onSlideFinish()                 //滑动结束的回调
    }
}