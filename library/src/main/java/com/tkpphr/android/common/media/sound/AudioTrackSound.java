package com.tkpphr.android.common.media.sound;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.support.annotation.NonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class AudioTrackSound implements Sound,Runnable{
	private AudioTrack audioTrack;
	private OnSoundStoppedListener onSoundStoppedListener;
	private int bit;
	private int channel;
	private int rate;
	private byte[] data;
	private Thread thread;
	private int markerInFrames;
	private static final int WAVE_HEADER_SIZE = 44;

	public AudioTrackSound(@NonNull String filePath){
		FileInputStream inputStream=null;
		try {
			inputStream=new FileInputStream(filePath);
			byte[] data=new byte[inputStream.available()];
			inputStream.read(data,0,data.length);
			initialize(data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public AudioTrackSound(@NonNull byte[] fileData){
		initialize(fileData);
	}

	@Override
	public void play() {
		if(audioTrack==null){
			int bufSize = AudioTrack.getMinBufferSize(rate, channel, bit);
			audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, rate, channel, bit, bufSize, AudioTrack.MODE_STREAM);
			audioTrack.setNotificationMarkerPosition(markerInFrames);
			audioTrack.setPlaybackPositionUpdateListener(new AudioTrack.OnPlaybackPositionUpdateListener() {
				@Override
				public void onMarkerReached(AudioTrack audioTrack) {
					if(onSoundStoppedListener!=null){
						onSoundStoppedListener.onSoundStopped(AudioTrackSound.this);
					}
				}

				@Override
				public void onPeriodicNotification(AudioTrack audioTrack) {

				}
			});
			thread=new Thread(this);
			thread.start();
		}else {
			if (audioTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
				stop();
				play();
			}
		}
	}

	@Override
	public void stop(){
		if(audioTrack!=null){
			audioTrack.stop();
			audioTrack.release();
			audioTrack=null;
			thread=null;
		}
	}

	@Override
	public void release() {
		if(audioTrack!=null){
			audioTrack.setPlaybackPositionUpdateListener(null);
			audioTrack.setNotificationMarkerPosition(0);
			audioTrack.stop();
			audioTrack.release();
			audioTrack=null;
			thread=null;
		}
	}

	@Override
	public void run() {
		if(audioTrack!=null){
			audioTrack.play();
			audioTrack.write(data, 0, data.length);
		}
	}

	public int getPlayState(){
		if(audioTrack==null){
			return 1;
		}
		return audioTrack.getPlayState();
	}

	public void setOnSoundStoppedListener(OnSoundStoppedListener onSoundStoppedListener) {
		this.onSoundStoppedListener = onSoundStoppedListener;
	}

	private void initialize(@NonNull byte[] fileData){
		ByteBuffer buffer = ByteBuffer.allocate(WAVE_HEADER_SIZE);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.put(fileData, 0, WAVE_HEADER_SIZE);
		buffer.rewind();
		buffer.position(buffer.position() + 22);
		this.channel = buffer.getShort()==1 ? AudioFormat.CHANNEL_OUT_MONO : AudioFormat.CHANNEL_OUT_STEREO;
		this.rate = buffer.getInt();
		buffer.position(buffer.position() + 6);
		this.bit = buffer.getShort()==16 ? AudioFormat.ENCODING_PCM_16BIT : AudioFormat.ENCODING_PCM_8BIT;
		buffer.position(40);
		this.data = Arrays.copyOfRange(fileData, WAVE_HEADER_SIZE, buffer.getInt());
		if (channel == AudioFormat.CHANNEL_OUT_MONO) {
			if(bit==AudioFormat.ENCODING_PCM_8BIT) {
				markerInFrames=data.length;
			}else {
				markerInFrames=data.length/2;
			}
		} else {
			if(bit==AudioFormat.ENCODING_PCM_8BIT) {
				markerInFrames = data.length / 2;
			}else {
				markerInFrames = data.length / 4;
			}
		}
	}


}
