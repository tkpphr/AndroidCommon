package com.tkpphr.android.common.graphics;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class AsyncDrawable extends BitmapDrawable{
	private WeakReference<AsyncImageTask> imageTaskReference;

	public AsyncDrawable(Context context, AsyncImageTask imageTask) {
		super(context.getResources(), BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_menu_gallery));
		this.imageTaskReference = new WeakReference<>(imageTask);
	}

	public AsyncDrawable(Context context, AsyncImageTask imageTask, @DrawableRes int loadingDrawableResId) {
		super(context.getResources(), BitmapFactory.decodeResource(context.getResources(), loadingDrawableResId));
		this.imageTaskReference = new WeakReference<>(imageTask);
	}


	@Nullable
	public AsyncImageTask getAsyncImageTask() {
		if(imageTaskReference==null){
			return null;
		}
		return imageTaskReference.get();
	}
}
