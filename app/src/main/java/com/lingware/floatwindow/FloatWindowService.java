package com.lingware.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FloatWindowService extends Service {
    MyWindowManager myWindowManager;

    public FloatWindowService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(Settings.canDrawOverlays(getApplicationContext())) {
                showWindow();
            } else {
                Intent  intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        } else {
            showWindow();
        }

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void showWindow() {
        if(myWindowManager == null) {
            myWindowManager = new MyWindowManager(getApplicationContext());
        }

        myWindowManager.createFLoatWindowVIew(getApplicationContext());
    }

    private void removeWindow() {
        if(myWindowManager == null) {
            return;
        }

        myWindowManager.removeFloatView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(MessageEvent event) {
        Log.e("ZWY", "----------------------------> evnet : " + event.getType());
        if(event.getType() == 1){
            removeWindow();
        }
    };
}
