package com.wyb.srp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片加载类
 * */
public class ImageLoader {
    //图片缓存
    ImageCache mImageCache = new ImageCache();
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();
    //双缓存
    DoubleCache mDoubleCache = new DoubleCache();
    //是否使用SD卡缓存
    boolean isUseDiskCache = false;
    //使用双缓存
    boolean isUseDoubleCache = false;
    //线程池，线程数量为Cpu数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    //显示图片
    public void displayImage(final  String url,final ImageView imageView)
    {

        Bitmap bitmap = null;

        /**多了很多判断 */
        if(isUseDoubleCache)
        {
            bitmap = mDoubleCache.get(url);
        }
        else if(isUseDiskCache)
        {
            bitmap = mDiskCache.get(url);
        }
        else
        {
            bitmap = mImageCache.get(url);
        }

        if(bitmap!=null)
        {
            imageView.setImageBitmap(bitmap);
            return ;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if(bitmap == null)
                {
                    return ;
                }
                if(imageView.getTag().equals(url))
                {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }
    //下载图片
    public Bitmap downloadImage(String imageUrl)
    {
        Bitmap bitmap = null;
        try
        {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn
                    = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }



    public void useDiskCache(boolean useDiskCache)
    {
        isUseDiskCache = useDiskCache;
    }
    public void useDoubleCache(boolean useDoubleCache)
    {
        isUseDoubleCache = useDoubleCache;
    }
}
