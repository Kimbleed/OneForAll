package com.example.oneforall.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.oneforall.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG = "BaseActivity";
    protected  Context CTX;

    abstract int getContentId();
    abstract void initView();
    abstract void initData();
    protected Handler mHandler = new ActivityHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CTX = this;
        TAG = this.getClass().getSimpleName();
        int contentId = getContentId();
        setContentView(contentId == 0? R.layout.activity_default:contentId);
        initView();
        initData();
    }

    protected Activity getActivity(){
        return this;
    }

    public void handleMessage(Message msg){

    }

    private static class ActivityHandler extends Handler {
        WeakReference<BaseActivity> mRefActivity;
        private ActivityHandler(BaseActivity activity){
            this.mRefActivity = new WeakReference<>(activity);
        }

        public void handleMessage(Message msg){
            if(this.mRefActivity!=null){
                BaseActivity activity = (BaseActivity) this.mRefActivity.get();
                if(activity !=null && !activity.isFinishing()){
                    activity.handleMessage(msg);
                }
            }

        }

    }
}
