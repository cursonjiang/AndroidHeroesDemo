package com.cursonjiang.androidheroesdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 姜炳臣 on 2015/10/22.
 */
public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /**
     * 自定义测量宽度
     *
     * @param measureSpec 测量规格
     * @return 测量宽度
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) { //如果是具体值或者是match_parent
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) { //如果是wrap_content
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 自定义测量高度
     *
     * @param measureSpec 测量规格
     * @return 测量高度
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {//如果是具体值或者是match_parent
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) { //如果是wrap_content
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
    }
}
