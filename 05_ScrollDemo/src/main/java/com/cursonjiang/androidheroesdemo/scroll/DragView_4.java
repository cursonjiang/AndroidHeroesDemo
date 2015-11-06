package com.cursonjiang.androidheroesdemo.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 通过scrollBy 设置偏移量移动View
 * 设置偏移量的时候，要加“-”号，不然会相反方向移动
 * Created by 姜炳臣 on 2015/11/6.
 */
public class DragView_4 extends View {

    private static final String TAG = "DragView_4";
    private int lastX;
    private int lastY;

    public DragView_4(Context context) {
        super(context);
        initView();
    }

    public DragView_4(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragView_4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //视图坐标系
//        int x = (int) event.getX();
//        int y = (int) event.getY();

        //Android坐标系
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "x - lastX: " + (x - lastX));
                Log.i(TAG, "y - lastY: " + (y - lastY));
                ((View) getParent()).scrollBy(-(x - lastX), -(y - lastY));

                //使用Android坐标系这里要从新设置
                //视图坐标系就不用了
                lastX = x;
                lastY = y;
                break;
        }
        return true;
    }
}
