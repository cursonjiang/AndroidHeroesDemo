package com.cursonjiang.androidheroesdemo.ChatItemDemo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import com.cursonjiang.androidheroesdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天界面布局
 * Created by 姜炳臣 on 2015/11/6.
 */
public class ChatItemListViewDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_item_demo);

        ListView listView = (ListView) findViewById(R.id.chat_list_view);
        ChatItemBean chatItemBean1 = new ChatItemBean();
        chatItemBean1.setType(0);
        chatItemBean1.setText("在学习Android~");
        chatItemBean1.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        ChatItemBean chatItemBean2 = new ChatItemBean();
        chatItemBean2.setType(1);
        chatItemBean2.setText("要努力哦！");
        chatItemBean2.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.in_icon));

        ChatItemBean chatItemBean3 = new ChatItemBean();
        chatItemBean3.setType(0);
        chatItemBean3.setText("还想学习ios - -");
        chatItemBean3.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        ChatItemBean chatItemBean4 = new ChatItemBean();
        chatItemBean4.setType(1);
        chatItemBean4.setText("慢慢来，先把手头的学好");
        chatItemBean4.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.in_icon));

        ChatItemBean chatItemBean5 = new ChatItemBean();
        chatItemBean5.setType(0);
        chatItemBean5.setText("好的，我会加油的！");
        chatItemBean5.setIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        List<ChatItemBean> itemBeanList = new ArrayList<>();
        itemBeanList.add(chatItemBean1);
        itemBeanList.add(chatItemBean2);
        itemBeanList.add(chatItemBean3);
        itemBeanList.add(chatItemBean4);
        itemBeanList.add(chatItemBean5);

        listView.setAdapter(new ChatItemAdapter(this, itemBeanList));
    }
}
