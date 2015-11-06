package com.cursonjiang.androidheroesdemo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewDemo extends Activity {

    private static final String TAG = "ListViewDemo";

    /**
     * 第一次按下的Y坐标
     */
    private float mFirstY;
    /**
     * 当前的手指的坐标
     */
    private float mCurrentY;
    /**
     * 滑动的方向  0 ：向下  1：向上
     */
    private int direction;
    /**
     * 系统认为的最低滑动距离，超过这个距离，系统就定义为滑动状态了。
     */
    private int mTouchSlop;
    private boolean mShow = true;

    private Toolbar mToolbar;
    private ListView mListView;

    private List<String> mData;
    private ViewHolderAdapter viewHolderAdapter;
    private ObjectAnimator mObjectAnimator;

    private int lastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_demo);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add(i + "");
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setEmptyView(findViewById(R.id.empty_view));
        View headerView = new View(this);

        headerView.setLayoutParams(
                new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));

        mListView.addHeaderView(headerView);
        viewHolderAdapter = new ViewHolderAdapter();
        mListView.setAdapter(viewHolderAdapter);

        lastVisibleItemPosition = mListView.getLastVisiblePosition();

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add("new");
                viewHolderAdapter.notifyDataSetChanged();
                mListView.setSelection(mData.size() - 1);
            }
        });

        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        Log.i(TAG, "mFirstY: " + mFirstY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();
                        Log.i(TAG, "mCurrentY: " + mCurrentY);
                        if (mCurrentY - mFirstY > mTouchSlop) { //手指向下滑动的时候，mCurrentY会越来越大
                            direction = 0;//down
                        } else if (mFirstY - mCurrentY > mTouchSlop) { //手指向上滑的时候，mCurrentY会越来越小
                            direction = 1;//up;
                        }
                        if (direction == 1) {
                            if (mShow) {
                                toolbarAnim(1);//隐藏
                                mShow = !mShow;
                            }
                        } else if (direction == 0) {
                            if (!mShow) {
                                toolbarAnim(0);//显示
                                mShow = !mShow;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        //滑动停止时调用
                        Log.i(TAG, "onScrollStateChanged: SCROLL_STATE_IDLE" + "滑动停止");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        //稍微用一点力就会调用
                        Log.i(TAG, "onScrollStateChanged: SCROLL_STATE_FLING" + "用手指用力滑动");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        //一滑动就会调用
                        Log.i(TAG, "onScrollStateChanged: SCROLL_STATE_TOUCH_SCROLL" + "正在滚动");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0)
                    Log.i(TAG, "onScroll: " + "滚动到最后一个Item");

                Log.i(TAG, "lastVisibleItemPosition: " + lastVisibleItemPosition);
                Log.i(TAG, "firstVisibleItem: " + firstVisibleItem);

                if (firstVisibleItem > lastVisibleItemPosition)
                    Log.i(TAG, "onScroll: " + "向上滑");
                else if (firstVisibleItem < lastVisibleItemPosition)
                    Log.i(TAG, "onScroll: " + "向下滑");

                lastVisibleItemPosition = firstVisibleItem;
            }
        });
    }

    private void toolbarAnim(int flag) {
        if (mObjectAnimator != null && mObjectAnimator.isRunning()) {
            mObjectAnimator.cancel();
        }
        if (flag == 0) {
            mObjectAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), 0);
        } else {
            mObjectAnimator = ObjectAnimator.ofFloat(mToolbar, "translationY", mToolbar.getTranslationY(), -mToolbar.getHeight());
        }
        mObjectAnimator.start();
    }

    public class ViewHolderAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ListViewDemo.this).inflate(R.layout.item, null);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(mData.get(position));
            holder.img.setBackgroundResource(R.mipmap.ic_launcher);
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView title;
        public ImageView img;
    }
}
