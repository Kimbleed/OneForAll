package com.example.oneforall.activity;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

public class HandlerThreadActivity extends BaseActivity{
    @Override
    int getContentId() {
        return 0;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        MyHandlerThread threadHandler = new MyHandlerThread("cen");
        threadHandler.start();

        Handler handler1 = new Handler(threadHandler.looper);
    }

    public static class MyHandlerThread extends Thread{
        String name;
        public MyHandlerThread(String name) {
            this.name = name;
        }

        Looper looper;
        @Override
        public void run() {
            Looper.prepare();
            looper = Looper.myLooper();
            Looper.loop();
        }

        public Looper getLooper(){
            return looper;
        }
    }
}
