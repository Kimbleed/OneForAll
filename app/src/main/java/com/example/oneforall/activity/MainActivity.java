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

        mList.add(new ActivityInfo("Proxy",ProxyActivity.class));
        mList.add(new ActivityInfo("Retrofit",RetrofitActivity.class));
        mList.add(new ActivityInfo("OKHttp",OkhttpActivity.class));
        mList.add(new ActivityInfo("RxJava",RxJavaActivity.class));
        mList.add(new ActivityInfo("组件化测试",LoginActivity.class));
        mList.add(new ActivityInfo("ActivityManagerService",ActivityManagerServiceActivity.class));
        mList.add(new ActivityInfo("Kotlin",KotlinActivity.class));
        mList.add(new ActivityInfo("Thread Lock",ThreadActivity.class));
        mList.add(new ActivityInfo("PackageManager",PackageManagerActivity.class));
        mList.add(new ActivityInfo("ComposeTest",ComposeTestActivity.class));
        mList.add(new ActivityInfo("bindService",ServiceActivity.class));


    }

    @Override
    void initData() {

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