package com.example.oneforall.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.app.ActivityCompat;

public class PermissionHelper {

    public static final int REQ_PERMISSION_CODE = 0x12;

    /**
     *
     * @param context
     * @param permissions  Manifest.permission
     * @return
     */
    public static boolean checkPermission(Activity context,String[] permissions){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(permissions == null || permissions.length<=0){
                return true;
            }
            //检查权限是不是已经授予
            List<String> noOkPermissions = new ArrayList<>();
            for(String permission:permissions){
                if(ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_DENIED){
                    noOkPermissions.add(permission);
                }
            }

            //该权限已经授予，不再申请
            if(noOkPermissions.size()<=0){
                return true;
            }

            ActivityCompat.requestPermissions(context,noOkPermissions.toArray(new String[noOkPermissions.size()]),REQ_PERMISSION_CODE);

            return false;
        }

        return true;
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults,OnPermissionHandleOverListener listener ){
        if(requestCode != REQ_PERMISSION_CODE){
            return;
        }
        Map<String ,Integer> result = new HashMap<>();
        //仍有权限未授权
        boolean isHavePermissionNotOk = false;
        for(int i =0;i<Math.min(permissions.length,grantResults.length);i++){
            result.put(permissions[i],grantResults[i]);
            if(grantResults[i] == PackageManager.PERMISSION_DENIED ){
                isHavePermissionNotOk = true;
            }
        }

        if(listener !=null){
            listener.onHandleOver(!isHavePermissionNotOk,result);
        }
    }

    public interface OnPermissionHandleOverListener{
        void onHandleOver(boolean isExactly, Map<String,Integer> result);
    }
}
