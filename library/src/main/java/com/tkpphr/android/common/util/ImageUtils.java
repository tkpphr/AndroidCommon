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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {
    private ImageUtils(){}

    public static Point getImageSize(String filePath){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(filePath,options);
        return new Point(options.outWidth,options.outHeight);
    }

    public static Point getImageSize(byte[] data){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeByteArray(data,0,data.length,options);
        return new Point(options.outWidth,options.outHeight);
    }

    @Nullable
    public static Bitmap createBitmap(String filePath, int inSampleSize){
        return createBitmap(filePath,inSampleSize,new Matrix());
    }

    @Nullable
    public static Bitmap createBitmap(byte[] data, int inSampleSize){
        return createBitmap(data,inSampleSize,new Matrix());
    }

    @Nullable
    public static Bitmap createBitmap(String filePath, int inSampleSize, Matrix transform){
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeFile(filePath,options);
            options.inJustDecodeBounds=false;
            options.inSampleSize=inSampleSize;
            return Bitmap.createBitmap(
                    BitmapFactory.decodeFile(filePath,options),
                    0,0,options.outWidth,options.outHeight,
                    transform,true);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Bitmap createBitmap(byte[] data, int sampleSize, Matrix transform){
        if(data==null){
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeByteArray(data,0,data.length,options);
            options.inJustDecodeBounds=false;
            options.inSampleSize=sampleSize;
            return Bitmap.createBitmap(
                    BitmapFactory.decodeByteArray(data,0,data.length,options),
                    0,0,options.outWidth,options.outHeight,
                    transform,true);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static int getMinimumSampleSize(@NonNull String filePath,@NonNull Point requestSize){
        return calculateMinimumSampleSize(getImageSize(filePath),requestSize);
    }

    public static int getMinimumSampleSize(@NonNull byte[] data,@NonNull Point requestSize){
        return calculateMinimumSampleSize(getImageSize(data),requestSize);
    }

    private static int calculateMinimumSampleSize(@NonNull Point srcSize,@NonNull Point requestSize){
        if(requestSize.x>=srcSize.x && requestSize.y>=srcSize.y) {
            return 1;
        }
        float widthRatio=(float)srcSize.x/(float)requestSize.x;
        float heightRatio=(float)srcSize.y/(float)requestSize.y;
        float ratio=widthRatio > heightRatio ? widthRatio : heightRatio;
        int i=0;
        if(Math.floor(ratio)==1){
            return 1;
        }
        while(true){
            float pow=(float)Math.pow(2,i);
            pow-=pow/4;
            if(Math.round(ratio)<=pow){
                return (int)Math.pow(2,i);
            }
            i++;
        }
    }

    @Nullable
    public static Bitmap transformedBitmap(@NonNull Bitmap src, Matrix transform){
        return Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),transform,true);
    }

    @Nullable
    public static Bitmap compressedBitmap(@NonNull Bitmap src, Bitmap.CompressFormat format, int quality){
        byte[] data= compressedBitmapData(src,format,quality);
        if(data==null){
            return null;
        }
        return BitmapFactory.decodeByteArray(data,0,data.length);
    }

    @Nullable
    public static byte[] compressedBitmapData(@NonNull Bitmap src, Bitmap.CompressFormat format, int quality){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        src.compress(format, quality, byteArrayOutputStream);
        byte[] data=byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }
}
