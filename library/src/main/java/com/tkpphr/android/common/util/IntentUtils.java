package com.tkpphr.android.common.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import java.io.File;

public class IntentUtils {
    private IntentUtils(){}



    @Nullable
    public static Intent createSpeechRecognitionIntent(String message){
        try{
            Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, message);
            return intent;
        }catch(ActivityNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static Intent createGetContentIntent(String mimeType){
        Intent intent;
        intent=new Intent();
        intent.setType(mimeType);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        return intent;
    }

    @NonNull
    public static Intent createSendFileIntent(Context context, File file, String mimeType){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType(mimeType);
        if(Build.VERSION.SDK_INT < 24){
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        }else {
            Uri uri = FileProvider.getUriForFile(context,context.getPackageName() + ".provider",file);
            intent.putExtra(Intent.EXTRA_STREAM,uri);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        return intent;
    }

}
