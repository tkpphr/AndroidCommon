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
