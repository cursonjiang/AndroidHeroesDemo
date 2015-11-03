package com.cursonjiang.androidheroesdemo.custom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cursonjiang.androidheroesdemo.R;

/**
 * 复合控件
 * Created by 姜炳臣 on 2015/11/2.
 */
public class TopBar extends RelativeLayout {

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    private topbarClickListener mListener;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        //设置TopBar背景
        setBackgroundColor(0xFFF59563);

        //通过此方法可以将在attrs中定义的declare-styleable所有属性的值存储到TypedArray中
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        //从TypedArray中取出对应的值
        //left
        String leftText = typedArray.getString(R.styleable.TopBar_leftText);
        int leftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        Drawable leftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);

        //right
        String rightText = typedArray.getString(R.styleable.TopBar_rightText);
        int rightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        Drawable rightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);

        //title
        String title = typedArray.getString(R.styleable.TopBar_topTitle);
        int titleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        float titleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 10);

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组件元素赋值
        //左边按钮
        mLeftButton.setText(leftText);
        mLeftButton.setTextColor(leftTextColor);
        mLeftButton.setBackground(leftBackground);

        //右边按钮
        mRightButton.setText(rightText);
        mRightButton.setTextColor(rightTextColor);
        mRightButton.setBackground(rightBackground);

        //中间标题
        mTitleView.setText(title);
        mTitleView.setTextColor(titleTextColor);
        mTitleView.setTextSize(titleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        typedArray.recycle();

        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        //添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        //按钮的点击事件，不需要具体的实现，只需要调用接口的方法，回调的时候，会有具体的实现。
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
    }

    public void setOnTopBarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置按钮的显示与否
     *
     * @param id   通过id区分按钮
     * @param flag 通过flag区分是否显示
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(VISIBLE);
            } else {
                mRightButton.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(GONE);
            } else {
                mRightButton.setVisibility(GONE);
            }
        }
    }

    //接口对象，实现回调机制，通过映射的接口对象调用接口中的方法
    //而不用考虑如何实现，具体的实现由调用者去创建
    public interface topbarClickListener {
        void leftClick();

        void rightClick();
    }

}
