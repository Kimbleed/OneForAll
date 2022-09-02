package com.example.oneforall.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.oneforall.R;
import com.example.oneforall.aidl.IMusicPlayerAidlInterface;
import com.example.oneforall.service.MusicPlayerService;
import com.example.oneforall.service.StartService;
import com.example.oneforall.utils.Logger;

public class ServiceActivity extends BaseActivity{
    
    private Button btn0,btn1,btn2,btn3;

    private IMusicPlayerAidlInterface player;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.getInstance().i(TAG,"onServiceConnected");
            player = IMusicPlayerAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.getInstance().i(TAG,"onServiceDisconnected");
        }
    };

    @Override
    int getContentId() {
        return R.layout.activity_single_btn;
    }

    @Override
    void initView() {
        btn0 = findViewById(R.id.btn0);
        btn0.setText("bindService");

        btn1 = findViewById(R.id.btn1);
        btn1.setText("preparePlay");

        btn2 = findViewById(R.id.btn2);
        btn2.setText("startPlay");

        btn3 = findViewById(R.id.btn3);
        btn3.setText("pausePlay");
        
        
    }

    @Override
    void initData() {
        findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(ServiceActivity.this,MusicPlayerService.class),serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    player.preparePlay("fuck");
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    player.startPlay();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    player.pausePlay();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
