package com.lingware.floatwindow;

/**
 * Created by wuyiz on 2017/8/9.
 */

public class MessageEvent {
    private int type = 0;

    public MessageEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
