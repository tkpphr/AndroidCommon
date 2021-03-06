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

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

public class SpannableFactory {
	private SpannableFactory(){}

	@NonNull
	public static SpannableStringBuilder createForegroundSpan(String src, @ColorInt int color){
		return createForegroundSpan(src,color,src);
	}

	@NonNull
	public static SpannableStringBuilder createForegroundSpan(String src, @ColorInt int color, String... targets){
		SpannableStringBuilder srcBuilder=new SpannableStringBuilder();
		srcBuilder.append(src);
		String srcLowerCase=src.toLowerCase();
		for(String target : targets) {
			if(TextUtils.isEmpty(target)){
				continue;
			}
			String targetLowerCase=target.toLowerCase();
			int index = srcLowerCase.indexOf(targetLowerCase);
			while (index >= 0) {
				srcBuilder.setSpan(new ForegroundColorSpan(color), index, index + targetLowerCase.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				index = srcLowerCase.indexOf(targetLowerCase, index + target.length());
			}
		}
		return srcBuilder;
	}

	@NonNull
	public static SpannableStringBuilder createBackgroundSpan(String src, @ColorInt int color){
		return createBackgroundSpan(src,color,src);
	}

	@NonNull
	public static SpannableStringBuilder createBackgroundSpan(String src, @ColorInt int color, String... targets){
		SpannableStringBuilder srcBuilder=new SpannableStringBuilder();
		srcBuilder.append(src);
		String srcLowerCase=src.toLowerCase();
		for(String target : targets) {
			if(TextUtils.isEmpty(target)){
				continue;
			}
			String targetLowerCase=target.toLowerCase();
			int index = srcLowerCase.indexOf(targetLowerCase);
			while (index >= 0) {
				srcBuilder.setSpan(new BackgroundColorSpan(color), index, index + targetLowerCase.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				index = srcLowerCase.indexOf(targetLowerCase, index + target.length());
			}
		}
		return srcBuilder;
	}

}
