package com.sunfusheng.StickyHeaderListView.manager;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.view.GlideCircleTransform;

/**
 * Created by sunfusheng on 16/4/6.
 */
public class ImageManager {

    private Context mContext;
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public ImageManager(Context context) {
        this.mContext = context;
    }

    // 将资源ID转为Uri
    public Uri resourceIdToUri(int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    // 加载网络图片
    public void loadUrlImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .crossFade()
                .into(imageView);
    }

    // 加载drawable图片
    public void loadResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .crossFade()
                .into(imageView);
    }

    // 加载本地图片
    public void loadLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .crossFade()
                .into(imageView);
    }

    // 加载网络圆型图片
    public void loadCircleImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载drawable圆型图片
    public void loadCircleResImage(int resId, ImageView imageView) {
        Glide.with(mContext)
                .load(resourceIdToUri(resId))
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

    // 加载本地圆型图片
    public void loadCircleLocalImage(String path, ImageView imageView) {
        Glide.with(mContext)
                .load("file://" + path)
                .placeholder(R.color.font_black_6)
                .error(R.color.font_black_6)
                .crossFade()
                .transform(new GlideCircleTransform(mContext))
                .into(imageView);
    }

}
