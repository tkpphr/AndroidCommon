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

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

public class DialogUtils {
    private DialogUtils(){}

    @Nullable
    public static Button getPositiveButton(AlertDialog dialog){
        if(dialog==null) {
            return null;
        }else {
            return dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        }
    }

    @Nullable
    public static Button getNeutralButton(AlertDialog dialog){
        if(dialog==null) {
            return null;
        }else {
            return dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        }
    }

    @Nullable
    public static Button getNegativeButton(AlertDialog dialog){
        if(dialog==null) {
            return null;
        }else {
            return dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        }
    }

    public static void setPositiveButtonEnabled(AlertDialog dialog,boolean enabled){
        if(dialog!=null){
            getPositiveButton(dialog).setEnabled(enabled);
        }
    }

    public static void setNeutralButtonEnabled(AlertDialog dialog,boolean enabled){
        if(dialog!=null){
            getNeutralButton(dialog).setEnabled(enabled);
        }
    }

    public static void setNegativeButtonEnabled(AlertDialog dialog,boolean enabled){
        if(dialog!=null){
            getNegativeButton(dialog).setEnabled(enabled);
        }
    }

}
