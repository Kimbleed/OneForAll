package com.example.oneforall.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.oneforall.R;
import com.example.oneforall.utils.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PackageManagerActivity extends BaseActivity {
    @Override
    int getContentId() {
        return R.layout.activity_default;
    }

    @Override
    void initView() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> list = packageManager.getInstalledApplications(0);
        Logger.getInstance().i(TAG, list.size());
        Map<PackageInfo,CheckInfo> tempPackageInfos = new LinkedHashMap<>();
        for (ApplicationInfo appInfo : list) {
            Logger.getInstance().i(TAG,"遍历所有app",appInfo.loadLabel(packageManager)+"");
//            if((appInfo.flags &ApplicationInfo.FLAG_SYSTEM)!=0){
            if(appInfo.loadLabel(packageManager).equals("机核")){
                Logger.getInstance().i(TAG,"1");
                try{
                    PackageInfo packageInfo = packageManager.getPackageInfo(appInfo.packageName, PackageManager.GET_ACTIVITIES);
                    Logger.getInstance().i(TAG,"机核Activity数",packageInfo.activities.length);
                }
                catch (Exception e){

                }

                Logger.getInstance().i(TAG,"1");
            }


            Logger.getInstance().i(TAG, "应用包名", appInfo.name != null ? appInfo.name : "null", appInfo.packageName);
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(appInfo.packageName, PackageManager.GET_ACTIVITIES);
                Logger.getInstance().i(TAG, "activity数量", packageInfo.activities.length);
                List<String> rnActivityNames =  findActivityName(packageInfo.activities,new String[]{"reactnative","react"});
                List<String> flutterActivityNames =  findActivityName(packageInfo.activities,new String[]{"flutter"});
                List<String> uniActivityNames =  findActivityName(packageInfo.activities,new String[]{"uni"});

                if(rnActivityNames .size()>0){
                    tempPackageInfos.put(packageInfo,new CheckInfo("ReactNative or React",rnActivityNames));
                }

                if(flutterActivityNames .size()>0){
                    tempPackageInfos.put(packageInfo,new CheckInfo("flutter",flutterActivityNames));
                }

                if(uniActivityNames .size()>0){
                    tempPackageInfos.put(packageInfo,new CheckInfo("uni",uniActivityNames));
                }
            } catch (Exception e) {

            }


//            }
        }

        try{
            Logger.getInstance().i(TAG,"有ReactNative的包名",tempPackageInfos.size());
            for(Map.Entry<PackageInfo,CheckInfo> entry:tempPackageInfos.entrySet()){
                String appName =entry.getKey().applicationInfo.loadLabel(packageManager)+"";
                Logger.getInstance().i(TAG,"应用名",appName,"关键字",entry.getValue().flag);

                for(String activityName:entry.getValue().rnActivityNames){
                    Logger.getInstance().i(TAG,"---",appName,"包名:",entry.getKey().packageName,"页面名",activityName);
                }

            }
        }
        catch (Exception e){

        }

        Logger.getInstance().i(TAG);
    }

    @Override
    void initData() {

    }

    private List<String> findActivityName(android.content.pm.ActivityInfo[] infos, String[] findNames){
        List<String> activityNames = new ArrayList<>();
        for(android.content.pm.ActivityInfo info:infos){
            for(String findName:findNames){
                if(info.name.indexOf(findName)>=0){
                    activityNames.add(info.name);
                }
            }

        }
        return activityNames;

    }

    class CheckInfo{
        String flag;
        List<String> rnActivityNames;

        public CheckInfo(String flag, List<String> rnActivityNames) {
            this.flag = flag;
            this.rnActivityNames = rnActivityNames;
        }
    }
}
