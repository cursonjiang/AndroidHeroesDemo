package com.cursonjiang.androidheroesdemo.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 通过获取ViewGroup.MarginLayoutParams
 * 然后设置setLayoutParams来设置位置
 * ViewGroup.MarginLayoutParams是设置Margin的值
 * Created by 姜炳臣 on 2015/11/6.
 */
public class DragView_3 extends View {

    private int lastX;
    private int lastY;

    public DragView_3(Context context) {
        super(context);
        initView();
    }

    public DragView_3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + (x - lastX);
                layoutParams.topMargin = getTop() + (y - lastY);
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
