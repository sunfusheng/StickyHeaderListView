package com.sunfusheng.StickyHeaderListView.view.GildeImageView;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by sunfusheng on 2017/1/23.
 */
public class GlideImageLoader {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String SEPARATOR = "/";
    private ImageView mImageView;

    public GlideImageLoader(ImageView view) {
        mImageView = view;
    }

    // 将资源ID转为Uri
    public Uri resIdToUri(int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + mImageView.getContext().getPackageName() + SEPARATOR + resourceId);
    }

    // 加载网络图片
    public void loadNetImage(String url, int holderResId) {
        urlBuilder(url, holderResId).into(mImageView);
    }

    // 加载drawable图片
    public void loadResImage(@IdRes int resId, int holderResId) {
        resBuilder(resId, holderResId).into(mImageView);
    }

    // 加载本地图片
    public void loadLocalPathImage(String path, int holderResId) {
        urlBuilder("file://" + path, holderResId).into(mImageView);
    }

    // 加载网络圆型图片
    public void loadNetCircleImage(String url, int holderResId) {
        urlBuilder(url, holderResId)
                .transform(new GlideCircleTransform(mImageView.getContext()))
                .into(mImageView);
    }

    // 加载drawable圆型图片
    public void loadLocalResCircleImage(int resId, int holderResId) {
        resBuilder(resId, holderResId)
                .transform(new GlideCircleTransform(mImageView.getContext()))
                .into(mImageView);
    }

    // 加载本地圆型图片
    public void loadLocalPathCircleImage(String path, int holderResId) {
        urlBuilder("file://" + path, holderResId)
                .transform(new GlideCircleTransform(mImageView.getContext()))
                .into(mImageView);
    }

    // 创建 Res DrawableRequestBuilder
    public DrawableRequestBuilder resBuilder(int resId, int holderResId) {
        return uriBuilder(resIdToUri(resId), holderResId);
    }

    // 创建 Uri DrawableRequestBuilder
    public DrawableRequestBuilder uriBuilder(Uri uri, int holderResId) {
        return Glide.with(mImageView.getContext())
                .load(uri)
                .crossFade()
                .dontAnimate()
                .fallback(holderResId)
                .error(holderResId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE);
    }

    // 创建 Url DrawableRequestBuilder
    public DrawableRequestBuilder urlBuilder(final String url, int holderResId) {
        if (GlideManager.getInstance().isFailedUrl(url)) {
            return resBuilder(holderResId, holderResId);
        }
        return Glide.with(mImageView.getContext())
                .load(url)
                .crossFade()
                .dontAnimate()
                .fallback(holderResId)
                .error(holderResId)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        GlideManager.getInstance().putFailedUrl(url);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                });
    }

    public boolean isGif(String url) {
        if (TextUtils.isEmpty(url)) return false;
        if (url.endsWith(".gif")) return true;
        return false;
    }
}
