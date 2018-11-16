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
