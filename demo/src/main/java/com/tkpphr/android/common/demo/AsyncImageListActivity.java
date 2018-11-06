package com.tkpphr.android.common.demo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.tkpphr.android.common.demo.databinding.ActivityAsyncImageListBinding;
import com.tkpphr.android.common.graphics.AsyncImageTask;
import com.tkpphr.android.common.graphics.ImageProvider;
import com.tkpphr.android.common.util.IOUtils;
import com.tkpphr.android.common.util.ImageUtils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class AsyncImageListActivity extends AppCompatActivity{
    private ActivityAsyncImageListBinding binding;
    private ImageAdapter adapter;

    public static Intent createIntent(Context context){
        return new Intent(context,AsyncImageListActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new ImageAdapter(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_async_image_list);
        binding.gridViewImage.setNumColumns(3);
        binding.gridViewImage.setAdapter(adapter);
    }

    private static class ImageAdapter extends BaseAdapter{
        private Context context;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Integer getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.image_list_item,parent,false);
            }

            AsyncImageTask.tryExecute((ImageView)convertView.findViewById(R.id.image_view),new LoadedImageProvider(context));
            return convertView;
        }
    }

    private static class LoadedImageProvider implements ImageProvider {
        private Context context;

        public LoadedImageProvider(Context context) {
            this.context = context;
        }

        @Override
        public Bitmap getImage() {
            InputStream inputStream=null;
            byte[] data=null;
            try {
                inputStream=context.getAssets().open("load_image.bmp");
                data=new byte[inputStream.available()];
                inputStream.read(data);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }finally {
                IOUtils.closeInputStream(inputStream);
            }
            switch (new Random().nextInt(4)){
                case 0:
                    return BitmapFactory.decodeByteArray(data,0,data.length);
                case 1:
                    return ImageUtils.createBitmap(data,4);
                case 2:
                    Bitmap src= ImageUtils.createBitmap(data,2);
                    return ImageUtils.compressedBitmap(src, Bitmap.CompressFormat.JPEG,50);
                default:
                    return null;
            }

        }
    }
}
