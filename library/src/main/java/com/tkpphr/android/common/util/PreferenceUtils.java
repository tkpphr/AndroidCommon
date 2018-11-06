package com.tkpphr.android.common.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

public class PreferenceUtils {
    private PreferenceUtils(){}

    public static void resetPreference(Context context, int resId){
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();
        PreferenceManager.setDefaultValues(context,resId,true);
    }

    public static void resetPreferenceAsync(Context context, int resId){
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
        PreferenceManager.setDefaultValues(context,resId,true);
    }

    public static String getString(Context context,String key,@Nullable String defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key,defaultValue);
    }

    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key,defaultValue);
    }

    public static int getInt(Context context,String key,int defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key,defaultValue);
    }

    public static float getFloat(Context context,String key,float defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key,defaultValue);
    }

    public static long getLong(Context context,String key,long defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key,defaultValue);
    }
    
    public static Set<String> getStringSet(Context context, String key, @Nullable Set<String> defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key,defaultValue);
    }

    public static void putString(Context context,String key,@Nullable String value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key,value).commit();
    }

    public static void putBoolean(Context context,String key,boolean value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key,value).commit();
    }

    public static void putInt(Context context,String key,int value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key,value).commit();
    }

    public static void putFloat(Context context,String key,float value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key,value).commit();
    }

    public static void putLong(Context context,String key,long value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key,value).commit();
    }

    public static void putStringSet(Context context, @NonNull String key, @Nullable Set<String> value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(key,value).commit();
    }

    public static void putStringAsync(Context context,String key,@Nullable String value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key,value).apply();
    }

    public static void putBooleanAsync(Context context,String key,boolean value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key,value).apply();
    }

    public static void putIntAsync(Context context,String key,int value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key,value).apply();
    }

    public static void putFloatAsync(Context context,String key,float value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key,value).apply();
    }

    public static void putLongAsync(Context context,String key,long value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key,value).apply();
    }

    public static void putStringSetAsync(Context context, @NonNull String key, @Nullable Set<String> value){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(key,value).apply();
    }
    
}
