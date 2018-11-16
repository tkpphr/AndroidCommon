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
package com.tkpphr.android.common.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class AsyncImageTask extends AsyncTask<ImageProvider, Void, Bitmap> {
	private WeakReference<ImageView> imageViewReference;
	private ImageProvider imageProvider;
	private int failedDrawableResId;

	private AsyncImageTask(@NonNull ImageView imageView,@NonNull ImageProvider imageProvider) {
		this.imageViewReference = new WeakReference<>(imageView);
		this.imageProvider=imageProvider;
		this.failedDrawableResId =0;
	}

	private AsyncImageTask(@NonNull ImageView imageView,@NonNull ImageProvider imageProvider,int failedDrawableResId) {
		this.imageViewReference = new WeakReference<>(imageView);
		this.imageProvider=imageProvider;
		this.failedDrawableResId = failedDrawableResId;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(ImageProvider... imageProviders) {
		this.imageProvider =imageProviders[0];
		return imageProvider.getImage();
	}

	@Override
	protected void onPostExecute(@Nullable Bitmap bitmap) {
		if (isCancelled() || imageViewReference==null) {
			return;
		}
		ImageView imageView = imageViewReference.get();
		if (imageView == null) {
			return;
		}
		Drawable drawable = imageView.getDrawable();
		if(drawable instanceof AsyncDrawable && this==((AsyncDrawable)drawable).getAsyncImageTask() ){
			if(bitmap==null){
				imageView.setImageDrawable(getFailedIcon(imageView.getContext()));
			}else {
				imageView.setImageBitmap(bitmap);
			}
		}
	}

	public ImageProvider getImageProvider() {
		return imageProvider;
	}

	@Nullable
	private Drawable getFailedIcon(Context context){
		if(failedDrawableResId ==0){
			return ResourcesCompat.getDrawable(context.getResources(),android.R.drawable.ic_menu_report_image,null);
		}else {
			return ResourcesCompat.getDrawable(context.getResources(), failedDrawableResId,context.getTheme());
		}
	}

	public static void tryExecute(ImageView imageView,ImageProvider imageProvider){
		if(checkLoadingImage(imageView,imageProvider)){
			return;
		}
		AsyncImageTask asyncImageTask=new AsyncImageTask(imageView,imageProvider);
		AsyncDrawable drawable=new AsyncDrawable(imageView.getContext(),asyncImageTask);
		imageView.setImageDrawable(drawable);
		asyncImageTask.execute(imageProvider);
	}

	public static void tryExecute(ImageView imageView,ImageProvider imageProvider,@DrawableRes int loadingDrawableId,@DrawableRes int failedDrawableResId){
		if(checkLoadingImage(imageView,imageProvider)){
			return;
		}
		AsyncImageTask asyncImageTask=new AsyncImageTask(imageView,imageProvider,loadingDrawableId);
		AsyncDrawable drawable=new AsyncDrawable(imageView.getContext(),asyncImageTask,failedDrawableResId);
		imageView.setImageDrawable(drawable);
		asyncImageTask.execute(imageProvider);
	}

	private static boolean checkLoadingImage(ImageView imageView,ImageProvider imageProvider){
		Drawable drawable=imageView.getDrawable();
		if(!(drawable instanceof AsyncDrawable)){
			return false;
		}
		AsyncImageTask imageTask=((AsyncDrawable)drawable).getAsyncImageTask();
		if(imageTask==null){
			return false;
		}else {
			if(imageTask.getImageProvider()!=imageProvider) {
				imageTask.cancel(true);
				return false;
			}
		}
		return true;
	}
}