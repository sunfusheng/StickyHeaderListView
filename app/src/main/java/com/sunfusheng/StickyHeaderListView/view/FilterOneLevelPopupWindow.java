package com.sunfusheng.StickyHeaderListView.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.sunfusheng.StickyHeaderListView.R;

import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/22.
 */
public class FilterOneLevelPopupWindow {

    private Context mContext;
    private static final int PW_HEIGHT = 240;
    private PopupWindow mPopupWindow;


    public FilterOneLevelPopupWindow(Context context) {
        this.mContext = context;

        if (mPopupWindow == null) {
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.view_filter_one_level_layout, null);
            mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, false);

            View mask = ButterKnife.findById(contentView, R.id.view_bg);
            mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });

//            mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if (popupDismissListener != null) {
                        popupDismissListener.popupDismiss();
                    }
                }
            });
        }
    }

    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    public void showPopupWindow(View view, int filterPosition) {
//        View contentView;
//        switch (filterPosition) {
//            case 0:
//                contentView = View.inflate(mContext, R.layout.view_filter_two_level_layout, null);
//                mPopupWindow.setContentView(contentView);
//                break;
//            case 1:
//                contentView = View.inflate(mContext, R.layout.view_filter_one_level_layout, null);
//                mPopupWindow.setContentView(contentView);
//                break;
//            case 2:
//                contentView = View.inflate(mContext, R.layout.view_filter_one_level_layout, null);
//                mPopupWindow.setContentView(contentView);
//                break;
//        }
//        if (!isShowing()) {
//            mPopupWindow.showAsDropDown(view);
//        } else {
//            mPopupWindow.update();
//        }
        dismiss();
        mPopupWindow.showAsDropDown(view);

    }

    public void dismiss() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void setPopupFocusable(boolean focusable) {
        mPopupWindow.setFocusable(focusable);
    }

    private PopupDismissListener popupDismissListener;

    public interface PopupDismissListener {
        void popupDismiss();
    }

    public void setPopupDismissListener(PopupDismissListener popupDismissListener) {
        this.popupDismissListener = popupDismissListener;
    }
}
