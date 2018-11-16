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
