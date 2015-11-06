package com.cursonjiang.androidheroesdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cursonjiang.androidheroesdemo.onTouchEvent.OnTouchEventDemo;
import com.cursonjiang.androidheroesdemo.scroll.DragViewDemo;

/**
 * Created by 姜炳臣 on 2015/11/6.
 */
public class MainActivity extends Activity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, DragViewDemo.class);
    }

    public void btnOnTouchDemo(View view) {
        startActivity(new Intent(this, OnTouchEventDemo.class));
    }

    public void btnDragDemo_1(View view) {
        mIntent.putExtra("flag", 1);
        startActivity(mIntent);
    }

    public void btnDragDemo_2(View view) {
        mIntent.putExtra("flag", 2);
        startActivity(mIntent);
    }

    public void btnDragDemo_3(View view) {
        mIntent.putExtra("flag", 3);
        startActivity(mIntent);
    }

    public void btnDragDemo_4(View view) {
        mIntent.putExtra("flag", 4);
        startActivity(mIntent);
    }

    public void btnDragDemo_5(View view) {
        mIntent.putExtra("flag", 5);
        startActivity(mIntent);
    }


}
