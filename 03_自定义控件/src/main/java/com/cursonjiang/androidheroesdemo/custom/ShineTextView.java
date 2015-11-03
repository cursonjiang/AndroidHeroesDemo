package com.cursonjiang.androidheroesdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 颜色渐变TextView
 * Created by 姜炳臣 on 2015/11/2.
 */
public class ShineTextView extends TextView {

    private int mViewWidth = 0;
    private int mTranslate = 0;

    /**
     * 矩阵
     */
    private Matrix mGradientMatrix;

    /**
     * LinearGradient的作用是实现某一区域内颜色线性渐变效果
     */
    private LinearGradient mLinearGradient;

    public ShineTextView(Context context) {
        super(context);
    }

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 组件大小改变时回调
     *
     * @param w    新的高
     * @param h    新的高
     * @param oldw 旧的宽
     * @param oldh 旧的高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();//获取控件的宽度
            if (mViewWidth > 0) {
                Paint paint = getPaint();//获取当前TextView的Paint对象
                //渐变颜色
                mLinearGradient = new LinearGradient(
                        0,//x轴渐变起点
                        0,//y轴渐变起点
                        mViewWidth,//x轴渐变终点
                        0,//y轴渐变终点
                        new int[]{//渐变颜色
                                Color.BLUE,
                                0xffffffff,
                                Color.RED},
                        null,// 如果这是空的，颜色均匀分布，沿梯度线。
                        Shader.TileMode.MIRROR//平铺方式
                        //CLAMP  重复最后一个颜色至最后
                        //MIRROR 重复着色的图像水平或垂直方向以镜像方式填充会有翻转效果
                        //REPEAT 重复着色的图像水平或垂直方向
                );
                paint.setShader(mLinearGradient);//给给当前的TextView的Paint设置着色器
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 10;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);//设置矩阵的宽度，也是就平移的位置
            mLinearGradient.setLocalMatrix(mGradientMatrix);//设置渐变色在矩阵的位置
            postInvalidateDelayed(100);//100毫秒刷新一次
        }
    }
}
