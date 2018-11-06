package com.tkpphr.android.common.media;

import android.media.MediaDataSource;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.M)
public class BinaryDataSource extends MediaDataSource{
	private byte[] buffer;

	public BinaryDataSource(byte[] buffer){
		this.buffer = buffer;
	}

	@Override
	public int readAt(long position, byte[] bytes, int offset, int size) throws IOException {
		int length= buffer.length;
		if (position>=length) {
			return -1;
		}
		if (position+size>length) {
			size-=(position+size)-length;
		}
		System.arraycopy(buffer,(int)position,bytes,offset,size);
		return size;
	}

	@Override
	public long getSize() throws IOException {
		return buffer.length;
	}

	@Override
	public void close() throws IOException {

	}

}
