package com.example.oneforall.activity;

import android.widget.TextView;

import com.example.oneforall.R;
import com.example.oneforall.utils.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyActivity extends BaseActivity{
    @Override
    int getContentId() {
        return R.layout.activity_default;
    }

    @Override
    void initView() {
        ((TextView)findViewById(R.id.tv)).setText(this.getClass().getSimpleName());
    }

    @Override
    void initData() {

        final Person person = new Man();

        Logger.getInstance().i(TAG,"----------静态代理");
        Person staticProxy = new ManProxy((Man)person);
        staticProxy.speak();
        staticProxy.walk();
        staticProxy.work();


        Logger.getInstance().i(TAG,"----------动态代理");
        Person dynamicProxy = (Person)Proxy.newProxyInstance(this.getClassLoader(), new Class<?>[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Logger.getInstance().i(TAG,"肆无忌惮");
                if (method.getDeclaringClass() == Object.class) {
                    Logger.getInstance().i(TAG,"Object.class 的方法 invoke");
                    return method.invoke(this, objects);
                }
                Object result = method.invoke(person,objects);
                return result;
            }
        });
        dynamicProxy.speak();
        dynamicProxy.walk();
        dynamicProxy.work();

    }

    public static interface Person{
        void speak();
        void walk();
        void work();
    }

    public static class Man implements Person{
        @Override
        public void speak() {
            Logger.getInstance().i(ProxyActivity.TAG,"男人说话");
        }

        @Override
        public void walk() {
            Logger.getInstance().i(ProxyActivity.TAG,"男人走路");
        }

        @Override
        public void work() {
            Logger.getInstance().i(ProxyActivity.TAG,"男人工作");
        }
    }

    public static class ManProxy implements Person{
        public Man man;
        public boolean isResting = true;

        public ManProxy(Man man) {
            this.man = man;
        }

        @Override
        public void speak() {
            man.speak();
            Logger.getInstance().i(ProxyActivity.TAG,"很大声");
        }

        @Override
        public void walk() {

            man.walk();
            Logger.getInstance().i(ProxyActivity.TAG,"去商场");
        }

        @Override
        public void work() {
            if(isResting){
                Logger.getInstance().i(ProxyActivity.TAG,"男人今天休息");
            }
            man.work();
        }
    }
}
