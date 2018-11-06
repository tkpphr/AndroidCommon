package com.tkpphr.android.common.demo;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tkpphr.android.common.media.sound.AudioTrackSound;
import com.tkpphr.android.common.media.sound.MediaPlayerSound;
import com.tkpphr.android.common.media.sound.Sound;


public class SoundButton extends AppCompatImageView{
	private Sound sound;
	private boolean isPlaying;
	private Drawable playIcon;
	private Drawable stopIcon;

	public SoundButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.playIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_play_arrow,null);
		this.stopIcon = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_stop,null);
		TypedValue outValue=new TypedValue();
		getContext().getTheme().resolveAttribute(android.R.attr.textColorPrimary,outValue,true);
		int color=ResourcesCompat.getColor(getResources(), outValue.resourceId,getContext().getTheme());
		this.playIcon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
		this.stopIcon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
		this.setImageDrawable(playIcon);
		getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,outValue,true);
		setScaleType(ScaleType.CENTER);
		setBackgroundResource(outValue.resourceId);
		setEnabled(false);
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (isPlaying) {
					stop();
				} else {
					play();
				}
			}
		});
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if(this.sound!=null){
			this.sound.stop();
			this.sound.release();
		}
		this.setImageDrawable(null);
	}

	public void setSound(Sound sound) {
		if(this.sound!=null){
			this.sound.stop();
			this.sound.release();
		}
		this.sound = sound;
		if(sound instanceof AudioTrackSound){
			((AudioTrackSound) sound).setOnSoundStoppedListener(sound1 -> stop());
		}else if(sound instanceof MediaPlayerSound){
			((MediaPlayerSound)sound).setOnSoundStoppedListener(sound1 -> stop());
		}
		setEnabled((this.sound!=null));
		this.setImageDrawable(playIcon);
	}

	public void play(){
		if(sound==null || isPlaying){
			return;
		}
		this.setImageDrawable(stopIcon);
		this.isPlaying=true;
		this.sound.play();
	}

	public void stop(){
		if(sound==null || !isPlaying){
			return;
		}
		this.setImageDrawable(playIcon);
		this.isPlaying=false;
		this.sound.stop();
	}

}
