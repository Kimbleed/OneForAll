package com.example.oneforall.activity;

public class ClassLoaderActivity extends BaseActivity{
    @Override
    int getContentId() {
        return 0;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        ClassLoader.getSystemClassLoader();
    }
}
