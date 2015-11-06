package com.cursonjiang.androidheroesdemo.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Scroller的使用
 * Created by 姜炳臣 on 2015/11/6.
 */
public class DragView_5 extends View {

    private int lastX;
    private int lastY;

    private Scroller mScroller;

    public DragView_5(Context context) {
        super(context);
        initView(context);
    }

    public DragView_5(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragView_5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setBackgroundColor(Color.GRAY);
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过重绘来不断调用computeScroll
            //invalidate -> draw() -> computeScroll()
            invalidate();
        }
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
                ((View) getParent()).scrollBy(-(x - lastX), -(y - lastY));
                break;
            case MotionEvent.ACTION_UP:
                //手指离开时，执行滑动过程
                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }
}
