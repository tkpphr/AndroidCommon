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

import android.graphics.Matrix;
import android.graphics.Point;
import android.support.annotation.NonNull;

public class MathUtils {
    private MathUtils(){}

    public static <T extends Comparable<T>>  T clamp(T val,T min,T max){
        if(min.compareTo(max)>0){
            throw new IllegalArgumentException("max must be greater than min");
        }
        if(min.compareTo(val)>0){
            return min;
        }
        if(val.compareTo(max)>0){
            return max;
        }
        return val;
    }

    @NonNull
    public static Matrix createTransform(float x,float y,float sizeX,float sizeY,float degrees){
        Matrix transform=new Matrix();
        transform.postScale(sizeX,sizeY);
        transform.postRotate(degrees);
        transform.postTranslate(x,y);
        return transform;
    }

    public static int gcd(int val1,int val2){
        if (val1 < val2) {
            val1 += val2;
            val2 = val1 - val2;
            val1 -= val2;
        }
        int temp = val1 % val2;
        while (temp != 0) {
            val1 = val2;
            val2 = temp;
            temp = val1 % val2;
        }
        return val2;
    }

    @NonNull
    public static Point aspectRatio(int width, int height){
        int gcd= MathUtils.gcd(width,height);
        return new Point(width/gcd,height/gcd);
    }

}
