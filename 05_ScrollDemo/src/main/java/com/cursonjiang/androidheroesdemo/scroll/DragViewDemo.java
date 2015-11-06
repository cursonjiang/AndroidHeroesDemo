package com.cursonjiang.androidheroesdemo.scroll;

import android.app.Activity;
import android.os.Bundle;

import com.cursonjiang.androidheroesdemo.R;

/**
 * 滑动Demo
 * Created by 姜炳臣 on 2015/11/6.
 */
public class DragViewDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = getIntent().getIntExtra("flag", 1);

        switch (flag) {
            case 1:
                setContentView(R.layout.drag_view_1);
                break;
            case 2:
                setContentView(R.layout.drag_view_2);
                break;
            case 3:
                setContentView(R.layout.drag_view_3);
                break;
            case 4:
                setContentView(R.layout.drag_view_4);
                break;
            case 5:
                setContentView(R.layout.drag_view_5);
                break;
        }
    }


}
