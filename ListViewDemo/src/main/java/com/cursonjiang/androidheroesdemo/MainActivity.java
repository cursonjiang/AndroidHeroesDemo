package com.cursonjiang.androidheroesdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cursonjiang.androidheroesdemo.ChatItemDemo.ChatItemListViewDemo;

/**
 * Created by 姜炳臣 on 2015/11/6.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnListView(View view) {
        startActivity(new Intent(this, ListViewDemo.class));
    }

    public void btnChatDemo(View view) {
        startActivity(new Intent(this, ChatItemListViewDemo.class));
    }
}
