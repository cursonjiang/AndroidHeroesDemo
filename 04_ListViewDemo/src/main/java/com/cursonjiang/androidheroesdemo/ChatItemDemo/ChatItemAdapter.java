package com.cursonjiang.androidheroesdemo.ChatItemDemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cursonjiang.androidheroesdemo.R;

import java.util.List;

/**
 * Created by 姜炳臣 on 2015/11/6.
 */
public class ChatItemAdapter extends BaseAdapter {

    private List<ChatItemBean> mItemBeanList;
    private LayoutInflater mLayoutInflater;

    public ChatItemAdapter(Context context, List<ChatItemBean> itemBeanList) {
        mItemBeanList = itemBeanList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItemBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            if (getItemViewType(position) == 0) {
                viewHolder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.chat_item_in, null);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_in);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.text_in);
            } else {
                viewHolder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.chat_item_out, null);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_out);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.text_out);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setImageBitmap(mItemBeanList.get(position).getIcon());
        viewHolder.mTextView.setText(mItemBeanList.get(position).getText());
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        ChatItemBean chatItemBean = mItemBeanList.get(position);
        return chatItemBean.getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public final class ViewHolder {
        public ImageView icon;
        public TextView mTextView;
    }
}
