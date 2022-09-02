package com.example.oneforall.activity;

import com.example.oneforall.R;
import com.example.oneforall.utils.Logger;

public class ThreadActivity extends BaseActivity{
    @Override
    int getContentId() {
        return R.layout.activity_default;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        SynchronizedTest t1 =new SynchronizedTest();
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                t1.test("线程1");
            }
        });
        thread1.start();

        SynchronizedTest t2=new SynchronizedTest();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t2.test("线程2");
            }
        });
        thread2.start();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.getInstance().i("Thread.interrupted(");
                thread1.interrupt();
            }
        },1000);




    }

    public class SynchronizedTest {
        public  void test(String name){
            Object o=new Object();
            synchronized(o.getClass()){
                System.out.println(name+"开始执行");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(name+"执行完毕");
            }

        }
    }
}
