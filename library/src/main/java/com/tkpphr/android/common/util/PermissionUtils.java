/*
   Copyright 2018 tkpphr

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.tkpphr.android.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.ArrayRes;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    private PermissionUtils() {
    }

    public static boolean isGrantedPermissions(Context context, String... permissions) {
        for (String permission : permissions){
            if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public static boolean isGrantedPermissions(Context context,@ArrayRes int arrayResId) {
        return isGrantedPermissions(context,context.getResources().getStringArray(arrayResId));
    }

    public static boolean shouldShowRequestPermissionsRationale(Activity activity, String... permissions) {
        for (String permission : permissions){
            if(!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
                return false;
            }
        }
        return true;
    }

    public static boolean shouldShowRequestPermissionsRationale(Activity activity, int arrayResId) {
        return shouldShowRequestPermissionsRationale(activity,activity.getResources().getStringArray(arrayResId));
    }

    public static void requestPermissions(Activity activity, int requestCode,@ArrayRes int arrayResId){
        ActivityCompat.requestPermissions(activity,activity.getResources().getStringArray(arrayResId),requestCode);
    }

    public static void requestPermissions(Activity activity, int requestCode,String... permissions){
        ActivityCompat.requestPermissions(activity,permissions,requestCode);
    }

    public static void requestPermissionsDeniedOnly(Activity activity,int requestCode,@ArrayRes int arrayResId) {
        requestPermissionsDeniedOnly(activity,requestCode,activity.getResources().getStringArray(arrayResId));
    }

    public static void requestPermissionsDeniedOnly(Activity activity, int requestCode,String... permissions) {
        List<String> deniedPermissions=new ArrayList<>();
        for (String permission : permissions){
            if(ActivityCompat.checkSelfPermission(activity.getApplicationContext(),permission)!=PackageManager.PERMISSION_GRANTED){
                deniedPermissions.add(permission);
            }
        }
        if(deniedPermissions.size()!=0) {
            permissions=deniedPermissions.toArray(new String[deniedPermissions.size()]);
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }
}
