package com.example.oneforall.service;

import android.os.RemoteException;

import com.example.oneforall.aidl.IMusicPlayerAidlInterface;
import com.example.oneforall.utils.Logger;

public class MusicPlayer extends IMusicPlayerAidlInterface.Stub {
    private static final String TAG = "MusicPlayer";

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        Logger.getInstance().i(TAG,"basicType");
    }

    @Override
    public void preparePlay(String url) throws RemoteException {
        Logger.getInstance().i(TAG,"preparePlay",url);
    }

    @Override
    public void startPlay() throws RemoteException {
        Logger.getInstance().i(TAG,"startPlay");
    }

    @Override
    public void pausePlay() throws RemoteException {
        Logger.getInstance().i(TAG,"pausePlay");
    }

    @Override
    public void stopPlay() throws RemoteException {
        Logger.getInstance().i(TAG,"stopPlay");
    }
}
