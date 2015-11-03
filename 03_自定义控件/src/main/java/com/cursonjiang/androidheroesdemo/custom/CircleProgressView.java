package com.cursonjiang.androidheroesdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 姜炳臣 on 2015/11/3.
 */
public class CircleProgressView extends View {

    private int mMeasureWidth;
    private int mMeasureHeight;

    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Paint mArcPaint;

    private float mSweepValue = 66;
    private float mShowTextSize;
    private float mSweepAngle;
    private float mCircleXY;
    private float mRadius;

    private RectF mArcRectF;

    private String mShowText;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);
        initView();
    }

    private void initView() {
        float lenght;
        if (mMeasureHeight >= mMeasureWidth) {
            lenght = mMeasureWidth;
        } else {
            lenght = mMeasureHeight;
        }
        mCircleXY = lenght / 2;//取中心的位置
        mRadius = (float) (lenght * 0.5 / 2);//半径

        //创建画笔并开启抗锯齿
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        //矩形
        mArcRectF = new RectF(
                (float) (lenght * 0.1),
                (float) (lenght * 0.1),
                (float) (lenght * 0.9),
                (float) (lenght * 0.9));
        mSweepAngle = (mSweepValue / 100f) * 360f;
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (lenght * 0.1));//宽度
        mArcPaint.setStyle(Paint.Style.STROKE);

        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);//对齐方式
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    private String setShowText() {
        this.invalidate();
        return "大大大大大臣哥";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        //绘制弧线
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);
        //绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY + (mShowTextSize / 4), mTextPaint);
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        this.invalidate();
    }
}
