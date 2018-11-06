package com.tkpphr.android.common.util;

import android.support.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {
    private IOUtils(){
    }

    @Nullable
    public static byte[] convertObjectToByteArray(Object object){
        ByteArrayOutputStream byteArrayOutput = null;
        ObjectOutputStream objectOutput = null;
        try {
            byteArrayOutput = new ByteArrayOutputStream();
            objectOutput = new ObjectOutputStream(byteArrayOutput);
            objectOutput.writeObject(object);
            objectOutput.flush();
            return byteArrayOutput.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            closeOutputStream(objectOutput);
        }
    }

    @Nullable
    public static List<Object> convertByteArrayToObjects(byte[] data, int objectCount){
        ObjectInputStream objectInput = null;
        ArrayList<Object> objects=new ArrayList<>();
        try {
            objectInput = new ObjectInputStream(new ByteArrayInputStream(data));
            for(int i=0;i<objectCount;i++){
                objects.add(objectInput.readObject());
            }
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeInputStream(objectInput);
        }
    }

    public static void closeInputStream(InputStream inputStream){
        if(inputStream!=null){
            try {
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void closeOutputStream(OutputStream outputStream){
        if(outputStream!=null){
            try {
                outputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
