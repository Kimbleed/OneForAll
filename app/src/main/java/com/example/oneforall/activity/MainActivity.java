package com.example.oneforall.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.login.LoginActivity;
import com.example.oneforall.R;
import com.example.oneforall.activity.rn_page.ui.Buz1Activity;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView rv;
    private MyRvAdapter mRvAdapter;
    private List<ActivityInfo> mList = new ArrayList<>();

    @Override
    int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        rv = findViewById(R.id.rv);
        mRvAdapter = new MyRvAdapter();
        rv.setAdapter(mRvAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CTX,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(layoutManager);

        //研究Java的JDK动态代理/CGLIB动态代理 和 静态代理
        mList.add(new ActivityInfo("Proxy",ProxyActivity.class));

        //研究Retrofit 框架源码
        mList.add(new ActivityInfo("Retrofit",RetrofitActivity.class));

        //研究Okhttp 框架源码
        mList.add(new ActivityInfo("OKHttp",OkhttpActivity.class));

        //研究Rxjava 框架源码
        mList.add(new ActivityInfo("RxJava",RxJavaActivity.class));

        //研究如何实现组件化
        mList.add(new ActivityInfo("组件化试验",LoginActivity.class));

        //ActivityManagerService AMS 研究（但目前啥都没写，我也忘了初衷）
        mList.add(new ActivityInfo("ActivityManagerService",ActivityManagerServiceActivity.class));

        //试验Kotlin的语法（布局仍是老方式，未使用UI框架 compose）
        mList.add(new ActivityInfo("Kotlin",KotlinActivity.class));

        //线程&锁 的学习
        mList.add(new ActivityInfo("Thread Lock",ThreadActivity.class));

        //PackageManager PMS 研究
        mList.add(new ActivityInfo("PackageManager",PackageManagerActivity.class));

        //UI框架 Compose的尝试
        mList.add(new ActivityInfo("ComposeTest",ComposeTestActivity.class));

        //Glide源码学习
        mList.add(new ActivityInfo("Glide ",GlideDemoActivity.class));

        //Kotlin UI框架Compose的体验
        mList.add(new ActivityInfo("Kotlin Compose",ComposeTestActivity.class));

        //Service 的使用学习
        mList.add(new ActivityInfo("bindService",ServiceActivity.class));

        //AIDL 实操 预备
        mList.add(new ActivityInfo("AIDL",AidlActivity.class));

        //ReactNative Activity :Buz1Activity 分包-业务包1
        mList.add(new ActivityInfo("RN 分包-业务包1", Buz1Activity.class));


    }

    @Override
    void initData() {
        ReactInstanceManager reactInstanceManager = ((ReactApplication)getApplication()).getReactNativeHost().getReactInstanceManager();
        if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
            reactInstanceManager.createReactContextInBackground();
            reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                @Override
                public void onReactContextInitialized(ReactContext context) {

                }
            });
        }

    }
    private class MyRvAdapter extends RecyclerView.Adapter<RvHolder>{


        @NonNull
        @Override
        public RvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CTX).inflate(R.layout.layout_item_tv,parent,false);
            return new RvHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RvHolder holder,int position) {
            final int fPosition = position;
            holder.tv.setText(mList.get(position).name);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,mList.get(fPosition).clz));
                }
            });
            if(position%2 ==0){
                holder.itemView.setBackgroundColor(Color.parseColor("#6495ED"));
            }
            else{
                holder.itemView.setBackgroundColor(Color.parseColor("#1E90FF"));
            }

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private static class RvHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tv;
        public RvHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv = itemView.findViewById(R.id.tv);
        }
    }
}