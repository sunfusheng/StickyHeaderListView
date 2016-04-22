package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.sunfusheng.StickyHeaderListView.R;

import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/22.
 */
public class FilterOneLevelPopupWindow implements View.OnClickListener {

    private Activity mActivity;
    private boolean isShow = false;
    private static final int PW_HEIGHT = 240;
    private PopupWindow mPopupWindow;


    public FilterOneLevelPopupWindow(Activity activity) {
        this.mActivity = activity;

        initData();
        initView();
        initListener();
    }

    private void initData() {
    }

    private void initView() {
    }

    private void initListener() {
    }

    public void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.view_filter_one_level_layout, null);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        View mask = ButterKnife.findById(contentView, R.id.view_bg);
        mask.setOnClickListener(this);

//        mPopupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        mPopupWindow.showAsDropDown(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_bg:
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                break;
        }
    }
}
