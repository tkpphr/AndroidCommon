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
