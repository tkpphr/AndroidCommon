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
package com.tkpphr.android.common.media.sound;

import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.tkpphr.android.common.media.BinaryDataSource;

import java.io.IOException;

public class MediaPlayerSound implements Sound {
	private Object dataSource;
	private MediaPlayer mediaPlayer;
	private OnSoundStoppedListener onSoundStoppedListener;

	public MediaPlayerSound(String filePath){
		this.dataSource=filePath;
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public MediaPlayerSound(byte[] data){
		this.dataSource =new BinaryDataSource(data);
	}

	@Override
	public void release(){
		if(mediaPlayer!=null){
			mediaPlayer.setOnCompletionListener(null);
			mediaPlayer.stop();
			mediaPlayer.reset();
			mediaPlayer.release();
			mediaPlayer=null;
		}
	}

	@Override
	public void play(){
		if(mediaPlayer!=null){
			return;
		}
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			mediaPlayer = createMediaPlayer();
		}else{
			mediaPlayer = createMediaPlayerCompat();
		}
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mediaPlayer) {
				release();
				if(onSoundStoppedListener !=null){
					onSoundStoppedListener.onSoundStopped(MediaPlayerSound.this);
				}
			}
		});

		try {
			mediaPlayer.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mediaPlayer.start();
	}

	@Override
	public void stop() {
		release();
	}

	public void setOnSoundStoppedListener(OnSoundStoppedListener onSoundStoppedListener) {
		this.onSoundStoppedListener = onSoundStoppedListener;
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	private MediaPlayer createMediaPlayer(){
		MediaPlayer mediaPlayer=new MediaPlayer();
		if(dataSource instanceof String) {
			try {
				mediaPlayer.setDataSource((String) dataSource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(dataSource instanceof MediaDataSource) {
			mediaPlayer.setDataSource((MediaDataSource) dataSource);
		}
		return mediaPlayer;
	}

	private MediaPlayer createMediaPlayerCompat(){
		MediaPlayer mediaPlayer=new MediaPlayer();
		if(dataSource instanceof String) {
			try {
				mediaPlayer.setDataSource((String) dataSource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mediaPlayer;
	}
}