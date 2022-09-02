package com.example.oneforall.activity;

import com.example.oneforall.utils.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolActivity extends BaseActivity{
    @Override
    int getContentId() {
        return 0;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
//        ExecutorService executorService = new ThreadPoolExecutor(0,Integer.MAX_VALUE, 60, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        ExecutorService executorService = new ThreadPoolExecutor(0,Integer.MAX_VALUE, 60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1));

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Logger.getInstance().i(TAG,"线程1 ");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Logger.getInstance().i(TAG,"线程2 ");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Logger.getInstance().i(TAG,"线程3 ");
            }
        });
    }
}
