package com.example.oneforall.activity.rn_page.ui;

import com.example.oneforall.activity.rn_page.RnBundle;
import com.facebook.react.AsyncReactActivity;

public class Buz1Activity extends AsyncReactActivity {
    @Override
    protected String getMainComponentName() {
        return "reactnative_multibundler";
    }

    @Override
    protected RnBundle getBundle(){
        RnBundle bundle = new RnBundle();
        bundle.scriptType = ScriptType.ASSET;
        bundle.scriptPath = "index.android.bundle";
        bundle.scriptUrl = "index.android.bundle";
        return bundle;
    }
}