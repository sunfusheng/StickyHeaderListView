package com.sunfusheng.StickyHeaderListView.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by sunfusheng on 15/8/7.
 */
public class ToastUtil {

    private static Toast mToast;

    public static void show(Context context, String message) {
        if (context == null || TextUtils.isEmpty(message)) return;
        int duration;
        if (message.length() > 10) {
            duration = Toast.LENGTH_LONG; //如果字符串比较长，那么显示的时间也长一些。
        } else {
            duration = Toast.LENGTH_SHORT;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
