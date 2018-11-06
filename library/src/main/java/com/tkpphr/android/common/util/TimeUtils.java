package com.tkpphr.android.common.util;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
	private TimeUtils(){}

	@NonNull
	public static String millisToHms(long millis){
		return String.format(Locale.ROOT,"%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
	}

	@NonNull
	public static String getDateTime(Date date, int dateStyle,int timeStyle){
		return DateFormat.getDateTimeInstance(dateStyle,timeStyle,Locale.getDefault()).format(date);
	}

}
