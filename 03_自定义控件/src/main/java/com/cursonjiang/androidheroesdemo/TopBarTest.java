package com.cursonjiang.androidheroesdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.cursonjiang.androidheroesdemo.custom.TopBar;

/**
 * Created by 姜炳臣 on 2015/11/3.
 */
public class TopBarTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_test);
        TopBar topBar = (TopBar) findViewById(R.id.topbar);
        topBar.setOnTopBarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopBarTest.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopBarTest.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
        topBar.setButtonVisable(0, true);
    }
}
