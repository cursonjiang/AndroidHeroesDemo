package com.cursonjiang.androidheroesdemo.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 通过Android坐标系
 * event.getRawX()  event.getRawY()来获取位置
 * Created by 姜炳臣 on 2015/11/6.
 */
public class DragView_2 extends View {

    private int lastX;
    private int lastY;

    public DragView_2(Context context) {
        super(context);
        initView();
    }

    public DragView_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();//距离屏幕左边的距离
        int rawY = (int) event.getRawY();//距离屏幕顶边的距离
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录第一次按下的坐标
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的偏移量
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                //在当前位置的基础上加上偏移量，就是新的位置
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

                //效果与Layout方法一样
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);

                //重新设置初始坐标
                //使用绝对坐标系，执行完ACTION_MOVE以后必须要从新设置初始坐标
                //这样才能准确的获取偏移量
                lastX = rawX;
                lastY = rawY;
                break;
        }
        return true;
    }
}
