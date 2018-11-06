package com.tkpphr.android.common.demo;

import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tkpphr.android.common.demo.databinding.ActivityMainBinding;
import com.tkpphr.android.common.os.Vibration;
import com.tkpphr.android.common.util.MathUtils;
import com.tkpphr.android.common.util.MenuUtils;
import com.tkpphr.android.common.util.PermissionUtils;
import com.tkpphr.android.common.util.SystemUtils;

import java.util.Locale;


public class MainActivity extends AppCompatActivity{
	private ActivityMainBinding binding;
	private Vibration vibration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
		vibration=new Vibration(this);
		setSupportActionBar(binding.toolbar);

		Point displaySize= SystemUtils.getDisplaySize(this);
		PointF dpSize= SystemUtils.getDisplayDPSize(this);
		Point aspectRatio= MathUtils.aspectRatio(displaySize.x,displaySize.y);
		binding.displayInfo.setText(String.format(Locale.ROOT,"<DisplayInfo>\nSize[x:%d y:%d]\nDPSize[x:%.0f y:%.0f]\nAspectRatio[x:%d y:%d]",displaySize.x,displaySize.y,dpSize.x,dpSize.y,aspectRatio.x,aspectRatio.y));
		binding.buttonImageList.setOnClickListener(v -> {
			startActivity(new Intent(getApplicationContext(),AsyncImageListActivity.class));
		});
		binding.buttonSoundTest.setOnClickListener(v -> {
			startActivity(new Intent(getApplicationContext(),SoundTestActivity.class));
		});
		binding.buttonVibrationTest.setOnClickListener(v->{
			vibration.vibrateContinuous(500);
		});
		PermissionUtils.requestPermissions(this,0, "android.permission.READ_EXTERNAL_STORAGE","android.permission.VIBRATE");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		vibration.release();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if(requestCode==0){
			if(!PermissionUtils.isGrantedPermissions(this,permissions)){
				Toast.makeText(this,"permission denied.",Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,0,0,"withIcon").setIcon(android.R.drawable.ic_menu_gallery).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
		MenuUtils.setIconVisibility(menu,true);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==0){
			MenuUtils.setMenuItemEnabled(item,false);
		}
		return super.onOptionsItemSelected(item);
	}

}
