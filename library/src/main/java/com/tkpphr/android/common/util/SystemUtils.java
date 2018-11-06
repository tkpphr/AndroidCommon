package com.tkpphr.android.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class SystemUtils {

    private SystemUtils(){}

    @Nullable
    public static Activity getActivityFromView(View view){
        return getActivityFromContext(view.getContext());
    }

    @Nullable
    public static Activity getActivityFromContext(Context context){
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    @Nullable
    public static Point getDisplaySize(Context context){
        Point point=new Point();
        WindowManager windowManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager==null){
            return null;
        }
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Point getDisplayRealSize(Context context){
        Point point=new Point();
        WindowManager windowManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(windowManager==null){
            return null;
        }
        windowManager.getDefaultDisplay().getRealSize(point);
        return point;
    }

    @NonNull
    public static PointF getDisplayDPSize(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new PointF(displayMetrics.widthPixels / displayMetrics.density,
                            displayMetrics.heightPixels / displayMetrics.density);
    }
}
