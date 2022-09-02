package com.example.oneforall.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.oneforall.utils.Logger;

import androidx.annotation.Nullable;

public class StartService extends Service {
    private static final String TAG = "StartService";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.getInstance().i(TAG, "onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.getInstance().i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        Logger.getInstance().i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.getInstance().i(TAG,"onBind");
        return null;
    }
}
