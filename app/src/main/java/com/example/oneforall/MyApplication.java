package com.example.oneforall;

import android.app.Application;

import com.example.oneforall.activity.rn_page.ScriptLoadUtil;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.smallnew.smartassets.RNSmartassetsPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;

import org.reactnative.maskedview.RNCMaskedViewPackage;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class MyApplication  extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return ScriptLoadUtil.MULTI_DEBUG;//是否是debug模式
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RNSmartassetsPackage(),
                    new RNCMaskedViewPackage(),
                    new RNGestureHandlerPackage(),
                    new ReanimatedPackage(),
                    new SafeAreaContextPackage(),
                    new RNScreensPackage()
            );
        }

        @Nullable
        @Override
        protected String getBundleAssetName() {
            return "platform.android.bundle";
        }

        @Override
        protected String getJSMainModuleName() {
            return "MultiDenugEntry";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
    }
}
