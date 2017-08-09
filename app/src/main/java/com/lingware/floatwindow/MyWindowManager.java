package com.lingware.floatwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by wuyiz on 2017/8/8.
 */

public class MyWindowManager {
    private WindowManager mWindowManger;
    private FloatWindowView floatWindowView;
    private WindowManager.LayoutParams floatViewParams;

    public MyWindowManager(Context context) {
        if(mWindowManger == null)
            mWindowManger = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    }

    private WindowManager getWindowManger(Context context) {
        if(mWindowManger == null) {
            mWindowManger = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        }

        return mWindowManger;
    }

    public void createFLoatWindowVIew(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        mWindowManger.getDefaultDisplay().getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        int screenHeight = outMetrics.heightPixels;

        if(floatWindowView == null) {
            floatWindowView = new FloatWindowView(context);
            if(floatViewParams == null) {
                floatViewParams = new WindowManager.LayoutParams();
                floatViewParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                floatViewParams.format = PixelFormat.RGBA_8888;
/*                floatViewParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;*/

                floatViewParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;

                floatViewParams.gravity = Gravity.LEFT | Gravity.TOP;
                floatViewParams.width = 500;
                floatViewParams.height = 500;
                floatViewParams.x = 100;
                floatViewParams.y = 100;
            }
            floatWindowView.setLayoutParams(floatViewParams);
            mWindowManger.addView(floatWindowView, floatViewParams);
        }
    }

    public void removeFloatView(){
        if(floatWindowView != null && mWindowManger != null) {
            mWindowManger.removeView(floatWindowView);
            floatWindowView = null;
        }
    }
}
