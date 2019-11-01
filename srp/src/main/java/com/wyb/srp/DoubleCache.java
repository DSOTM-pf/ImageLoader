package com.wyb.srp;

import android.graphics.Bitmap;

/**
 * 为了解决：用户使用内存缓存时不能使用SD卡缓存，使用SD卡缓存时不能使用内存缓存的问题
 * 双缓存，获取图片时先从内存缓存中获取，如果内存中没有缓存该图片，再从SD卡中获取
 * 缓存图片也是在内存和SD卡中都缓存一份
 * */
public class DoubleCache {
    ImageCache mMemoryCache = new ImageCache();
    DiskCache mDiskCache = new DiskCache();

    //先从内存缓存中获取图片，如果没有再从SD卡中获取
    public Bitmap get(String url)
    {
        Bitmap bitmap = mMemoryCache.get(url);
        if(bitmap == null)
        {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }
    //将图片缓存到内存和SD卡中
    public void put(String url,Bitmap bmp)
    {
        mMemoryCache.put(url,bmp);
        mDiskCache.put(url,bmp);
    }
}
