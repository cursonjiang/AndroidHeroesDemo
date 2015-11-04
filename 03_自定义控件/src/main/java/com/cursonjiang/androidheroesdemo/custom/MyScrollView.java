package com.cursonjiang.androidheroesdemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * 继承ViewGroup自定义ScrollView
 * Created by 姜炳臣 on 2015/11/4.
 */
public class MyScrollView extends ViewGroup {

    private static final String TAG = "MyScrollView";
    private int mLastY;
    private int mStart;
    private int mEnd;

    private int mScroolHeight;
    private Scroller mScroller;

    public MyScrollView(Context context) {
        super(context);
        initView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScroolHeight = dm.heightPixels;//获取屏幕的高度
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取子View的总数
        int childCount = getChildCount();
        //通过遍历测量每一个子View
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScroolHeight * childCount;//获取一个View的高度，然后乘以总数，就是整个ViewGroup的高度
        setLayoutParams(mlp);
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != GONE) {
                childAt.layout(
                        l,
                        i * mScroolHeight,
                        r,
                        (i + 1) * mScroolHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();//获取手指按下的位置
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScroolHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();//获取手指离开的位置
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0) {//大于0的时候，是向上滚动，
                    Log.i(TAG, "onTouchEvent: dScrollY > 0：" + dScrollY);
                    if (dScrollY < mScroolHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);//滚动回原位置
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, mScroolHeight - dScrollY);//滚动到下一页
                    }
                } else {//小于0的时候，是向下滚动
                    Log.i(TAG, "onTouchEvent: dScrollY < 0：" + dScrollY);
                    Log.i(TAG, "onTouchEvent: -dScrollY < 0：" + -dScrollY);
                    if (-dScrollY < mScroolHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -mScroolHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
