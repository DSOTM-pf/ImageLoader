package com.wyb.srp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 加入SD卡缓存
 * 因为内存缓存容易丢失
 * */
public class DiskCache {
    static String cacheDir = "sdcard/cache/";
    //从缓存中获取图片
    public Bitmap get(String url)
    {
        return BitmapFactory.decodeFile(cacheDir+url);
    }
    //将图片缓存到内存中
    public void put(String url,Bitmap bmp)
    {
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(cacheDir+url);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
