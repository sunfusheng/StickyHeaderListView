package com.sunfusheng.StickyHeaderListView.view.GildeImageView;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunfusheng on 2017/1/22.
 */
public class GlideManager {

    private static Map<String, Integer> map = new HashMap<>();

    private static GlideManager mInstance = new GlideManager();

    private static final int FAILED_TIME_THROTTLE = 1;

    private GlideManager() {
    }

    public static GlideManager getInstance() {
        return mInstance;
    }

    public boolean isFailedUrl(String url) {
        if (TextUtils.isEmpty(url)) return true;
        if (map.containsKey(url) && map.get(url) >= FAILED_TIME_THROTTLE) return true;
        return false;
    }

    public void putFailedUrl(String url) {
        if (TextUtils.isEmpty(url)) return;
        int failedTimes = 1;
        if (map.containsKey(url)) {
            failedTimes += map.get(url);
        }
        map.put(url, failedTimes);
    }
}
