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

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;

public class ResourcesUtils {
    private ResourcesUtils(){
    }

    public static int getIdFromAttr(Context context,@AttrRes int attrId){
        TypedValue outValue=new TypedValue();
        context.getTheme().resolveAttribute(attrId,outValue,true);
        return outValue.resourceId;
    }

    public static int getColor(Context context,@ColorRes int colorResId){
        return ResourcesCompat.getColor(context.getResources(), colorResId,context.getTheme());
    }

    @Nullable
    public static Drawable getDrawable(Context context,@DrawableRes int drawableResId){
        return ResourcesCompat.getDrawable(context.getResources(),drawableResId,context.getTheme());
    }

    @Nullable
    public static Drawable getDrawable(Context context,@DrawableRes int drawableResId, String colorString, PorterDuff.Mode mode){
        Drawable drawable=ResourcesCompat.getDrawable(context.getResources(),drawableResId,context.getTheme());
        if(drawable==null){
            return null;
        }
        setColorFilter(drawable,colorString,mode);
        return drawable;
    }

    @Nullable
    public static Drawable getDrawable(Context context, @DrawableRes int drawableResId, @ColorRes int colorResId, PorterDuff.Mode mode){
        Drawable drawable=ResourcesCompat.getDrawable(context.getResources(),drawableResId,context.getTheme());
        if(drawable==null){
            return null;
        }
        setColorFilter(context,drawable,colorResId,mode);
        return drawable;
    }

    public static void setColorFilter(Context context,Drawable drawable,@ColorRes int colorId,PorterDuff.Mode mode){
        int color=ResourcesCompat.getColor(context.getResources(),colorId,context.getTheme());
        drawable.setColorFilter(color,mode);
    }

    public static void setColorFilter(Drawable drawable,String colorString,PorterDuff.Mode mode){
        int color= Color.parseColor(colorString);
        drawable.setColorFilter(color,mode);
    }
}
