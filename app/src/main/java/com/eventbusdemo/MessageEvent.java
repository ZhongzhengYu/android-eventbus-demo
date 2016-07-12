package com.eventbusdemo;

/**
 * Created by YuZhongzheng on 2016/7/12.
 * <p/>
 * zhongzhengdev@gmail.com
 */
public class MessageEvent {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String message){
        this.message = message;
    }
}
