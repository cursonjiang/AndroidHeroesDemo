package com.cursonjiang.androidheroesdemo.ChatItemDemo;

import android.graphics.Bitmap;

/**
 * Created by 姜炳臣 on 2015/11/6.
 */
public class ChatItemBean {

    private int type;
    private String text;
    private Bitmap icon;

    public ChatItemBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
