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

import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.io.File;

public class SoundUtils {
	private SoundUtils(){}

	@RequiresApi(Build.VERSION_CODES.M)
	public static int getDuration(MediaDataSource mediaDataSource){
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		retriever.setDataSource(mediaDataSource);
		String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		retriever.release();
		if(TextUtils.isEmpty(duration)){
			return 0;
		}else {
			return Integer.parseInt(duration);
		}
	}

	public static int getDuration(String filePath){
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		retriever.setDataSource(filePath);
		String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		retriever.release();
		if(TextUtils.isEmpty(duration)){
			return 0;
		}else {
			return Integer.parseInt(duration);
		}
	}

	public static int getDuration(File file){
		return getDuration(file.getAbsolutePath());
	}
}
