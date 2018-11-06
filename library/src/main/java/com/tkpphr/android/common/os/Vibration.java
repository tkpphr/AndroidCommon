package com.tkpphr.android.common.os;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Vibration {
	private Vibrator vibrator;
	
	public Vibration(Context context){
		vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void vibrateContinuous(long duration){
		if(!vibrator.hasVibrator()){
			return;
		}
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
			vibrator.vibrate(duration);
		}else {
			vibrator.vibrate(VibrationEffect.createOneShot(duration,VibrationEffect.DEFAULT_AMPLITUDE));
		}
	}
	
	public void vibrateInterval(long duration){
		if(!vibrator.hasVibrator()){
			return;
		}
		long[] pattern=new long[16];
		for(int i=0;i<pattern.length;i++){
			pattern[i]=duration/pattern.length;
		}
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
			vibrator.vibrate(pattern,-1);
		}else {
			vibrator.vibrate(VibrationEffect.createWaveform(pattern,-1));
		}
	}
	
	public void stop(){
		if(vibrator.hasVibrator()){
			vibrator.cancel();
		}

	}
	
	public void release(){
		if(vibrator.hasVibrator()){
			vibrator.cancel();
		}
		vibrator=null;
	}


}
