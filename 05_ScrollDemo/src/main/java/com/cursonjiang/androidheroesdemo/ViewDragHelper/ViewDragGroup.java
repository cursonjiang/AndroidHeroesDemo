package com.cursonjiang.androidheroesdemo.ViewDragHelper;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 姜炳臣 on 15/11/8.
 */
public class ViewDragGroup extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;
    private int mWidth;

    public ViewDragGroup(Context context) {
        super(context);
        initView();
    }

    public ViewDragGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ViewDragGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper,此操作必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, mCallbackallback);
    }

    private ViewDragHelper.Callback mCallbackallback = new ViewDragHelper.Callback() {

        /**
         * 这个方法是可以指定当前创建ViewDragHelper的时候
         * 指定哪一个子View可以被移动,这里指定的是mMainView
         * @param child 子View
         * @param pointerId 父Id
         * @return 当前触摸的子View是否是child
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //如果当前触摸的child是mMainView时开始检测
            return mMainView == child;
        }


        /**
         * 水平方向滑动
         * @param child 子View
         * @param left 水平方向向左 child移动的距离
         * @param dx 较上一次的增量
         * @return left
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        /**
         * 垂直滑动
         * @param child 子View
         * @param top 水平方向向上 child移动的距离
         * @param dy 较上一次的增量
         * @return 因为是左右滑动, 所以这里返回0
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        /**
         * 手指离开屏幕后调用
         * @param releasedChild 手指离开屏幕以后子View的操作
         * @param xvel X速度指针
         * @param yvel Y速度指针
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //如果拉出的距离距左边小于500像素
            if (mMainView.getLeft() < 200) {
                //关闭菜单
                //相当于Scroller中的startScroll方法
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(ViewDragGroup.this);//滑动的动画
            } else {
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(ViewDragGroup.this);
            }

        }
    };

    /**
     * ViewDragHelper内部也是通过Scroller来实现平滑移动的
     * 所以需要重写computeScroll方法
     */
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
