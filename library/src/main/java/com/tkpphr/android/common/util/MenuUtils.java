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

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;

public class MenuUtils {
    private MenuUtils(){}

    public static void setIconVisibility(Menu menu,boolean visible){
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, visible);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setMenuItemEnabled(MenuItem menuItem, boolean enabled){
        menuItem.setEnabled(enabled);
        Drawable menuIcon=menuItem.getIcon();
        if(menuIcon!=null){
            menuIcon.mutate().setColorFilter(enabled ? Color.WHITE:Color.GRAY, PorterDuff.Mode.MULTIPLY);
        }
    }
}
