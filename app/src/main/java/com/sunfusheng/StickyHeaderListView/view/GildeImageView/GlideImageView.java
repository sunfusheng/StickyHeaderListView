package com.sunfusheng.StickyHeaderListView.view.GildeImageView;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;

/**
 * Created by sunfusheng on 2017/1/22.
 *
 * 设置 placeholder 有坑
 */
public class GlideImageView extends android.support.v7.widget.AppCompatImageView {

    private GlideImageLoader mLoader;

    public GlideImageView(Context context) {
        this(context, null);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLoader = new GlideImageLoader(this);
    }

    // 加载网络图片
    public void loadNetImage(String url, int holderResId) {
        mLoader.loadNetImage(url, holderResId);
    }

    // 加载drawable图片
    public void loadResImage(@IdRes int resId, int holderResId) {
        mLoader.loadResImage(resId, holderResId);
    }

    // 加载本地图片
    public void loadLocalPathImage(String path, int holderResId) {
        mLoader.loadLocalPathImage(path, holderResId);
    }

    // 加载网络圆型图片
    public void loadNetCircleImage(String url, int holderResId) {
        mLoader.loadNetCircleImage(url, holderResId);
    }

    // 加载drawable圆型图片
    public void loadLocalResCircleImage(int resId, int holderResId) {
        mLoader.loadLocalResCircleImage(resId, holderResId);
    }

    // 加载本地圆型图片
    public void loadLocalPathCircleImage(String path, int holderResId) {
        mLoader.loadLocalPathCircleImage(path, holderResId);
    }

}
