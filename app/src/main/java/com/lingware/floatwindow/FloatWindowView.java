package com.lingware.floatwindow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by wuyiz on 2017/8/8.
 */

public class FloatWindowView extends LinearLayout {
    private TextView mHelloWorld;

    public FloatWindowView(Context context) {
        this(context, null);
    }

    public FloatWindowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatWindowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWindowView(context);
    }

    private void initWindowView(Context context) {
        EventBus.getDefault().register(this);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.activity_main, this);

        mHelloWorld = (TextView) view.findViewById(R.id.textView3);

        mHelloWorld.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(1));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(NewMessageT event) {
        Log.e("ZWY", "x---------->xxx _---->  ");
    };
}
