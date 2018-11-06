package com.tkpphr.android.common.demo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.tkpphr.android.common.demo.databinding.ActivitySoundTestBinding;
import com.tkpphr.android.common.media.sound.AudioTrackSound;
import com.tkpphr.android.common.media.sound.MediaPlayerSound;
import com.tkpphr.android.common.util.IntentUtils;
import com.tkpphr.android.common.util.UriResolver;


public class SoundTestActivity extends AppCompatActivity {
    private ActivitySoundTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sound_test);
        binding.buttonSelectMediaPlayer.setOnClickListener(view -> {
            startActivityForResult(IntentUtils.createGetContentIntent("audio/*"),0);
        });
        binding.buttonSelectAudioTrack.setOnClickListener(view -> {
            startActivityForResult(IntentUtils.createGetContentIntent("audio/x-wav"),1);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.soundButtonAudioTrack.stop();
        binding.soundButtonMediaPlayer.stop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            String filePath = UriResolver.getFilePath(getApplicationContext(), data.getData());
            if(!TextUtils.isEmpty(filePath)) {
                if (requestCode == 0) {
                    binding.soundButtonMediaPlayer.setSound(new MediaPlayerSound(filePath));
                    binding.textViewMediaPlayerSoundPath.setText(filePath);
                } else if (requestCode == 1) {
                    binding.soundButtonAudioTrack.setSound(new AudioTrackSound(filePath));
                    binding.textViewAudioTrackSoundPath.setText(filePath);
                }
            }
        }
    }

}
