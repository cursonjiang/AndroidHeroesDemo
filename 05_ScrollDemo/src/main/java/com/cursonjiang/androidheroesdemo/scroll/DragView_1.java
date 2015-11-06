package com.cursonjiang.androidheroesdemo.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 通过试图坐标系
 * event.getX()  event.getY()来获取位置
 * Created by 姜炳臣 on 2015/11/6.
 */
public class DragView_1 extends View {

    private static final String TAG = "DragView";
    private int lastX;
    private int lastY;

    public DragView_1(Context context) {
        super(context);
        initView();
    }

    public DragView_1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();//View中距离左边的距离
        int y = (int) event.getY();//View中距离顶边的距离
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下的坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //在当前的位置上，加上移动的偏移量就是新的位置
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

                //效果与Layout方法一样
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
