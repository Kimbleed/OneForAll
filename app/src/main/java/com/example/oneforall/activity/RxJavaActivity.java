package com.example.oneforall.activity;

import com.example.oneforall.utils.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends BaseActivity {

    @Override
    int getContentId() {
        return 0;
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {

        RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
            @Override
            public Observable apply(@NonNull Observable observable) throws Exception {
                Logger.getInstance().i(TAG, "RxJavaPlugin 的 hook。", observable.getClass().getSimpleName());
                return observable;
            }
        });

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                Logger.getInstance().i(TAG, "subscribe start", emitter.getClass().getSimpleName());

                Logger.getInstance().i(TAG, "emitter.onNext");
                emitter.onNext("丢你");
                Logger.getInstance().i(TAG, "emitter.onNext");
                emitter.onNext("食7屎啦");
                Logger.getInstance().i(TAG, "emitter.onNext");
                emitter.onNext("扑街仔");
                Logger.getInstance().i(TAG, "emitter.onComplete");
                emitter.onComplete();
            }
        })
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull String s) throws Exception {
                        Logger.getInstance().i(TAG,"flatMap",s);
                        return Observable.just(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Logger.getInstance().i(TAG,"doOnNext ",o.getClass().getSimpleName()," "+o);
                    }
                }).subscribeOn(Schedulers.io());
//                .map(new Function<String, Observable<String>>() {
//            @Override
//            public Observable<String> apply(@NonNull String s) throws Exception {
//                Logger.getInstance().i(TAG, "apply ", s);
//                return Observable.just(s);
//            }
//        });

        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.getInstance().i(TAG,"Observer onSubscribe ",d.getClass().getSimpleName());
            }

            @Override
            public void onNext(@NonNull Object o) {
                Logger.getInstance().i(TAG,"Observer onNext ",o.getClass().getSimpleName(), " "+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Logger.getInstance().i(TAG,"Observer onComplete ");
            }
        });
//
//        observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Logger.getInstance().i(TAG, "Observer onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Integer o) {
//                Logger.getInstance().i(TAG, "Observer onNext:" + o);
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Logger.getInstance().i(TAG, "Observer onError");
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }
}
