package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunfusheng.StickyHeaderListView.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class FilterView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.tv_category)
    TextView tvCategory;
    @Bind(R.id.iv_category_arrow)
    ImageView ivCategoryArrow;
    @Bind(R.id.tv_sort)
    TextView tvSort;
    @Bind(R.id.iv_sort_arrow)
    ImageView ivSortArrow;
    @Bind(R.id.tv_filter)
    TextView tvFilter;
    @Bind(R.id.iv_filter_arrow)
    ImageView ivFilterArrow;
    @Bind(R.id.ll_category)
    LinearLayout llCategory;
    @Bind(R.id.ll_sort)
    LinearLayout llSort;
    @Bind(R.id.ll_filter)
    LinearLayout llFilter;
    @Bind(R.id.ll_filter_layout)
    LinearLayout llFilterLayout;

    private Context mContext;
    private boolean isStickyTop = false; // 是否吸附在顶部

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_filter_layout, this);
        ButterKnife.bind(this, view);

        initData();
        initView();
        initListener();
    }

    private void initData() {

    }

    private void initView() {
    }

    private void initListener() {
        llCategory.setOnClickListener(this);
        llSort.setOnClickListener(this);
        llFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_category:
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(0, false);
                }
                break;
            case R.id.ll_sort:
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(1, false);
                }
                break;
            case R.id.ll_filter:
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(2, false);
                }
                break;
        }
    }

    // 显示
    public void show(Activity activity) {
        FilterOneLevelPopupWindow oneLevelPopupWindow = new FilterOneLevelPopupWindow(activity);
        oneLevelPopupWindow.showPopupWindow(this);
    }

    // 隐藏
    public void hide() {
    }

    private OnFilterClickListener onFilterClickListener;

    public void setOnFilterClickListener(OnFilterClickListener onFilterClickListener) {
        this.onFilterClickListener = onFilterClickListener;
    }

    public interface OnFilterClickListener {
        void onFilterClick(int position, boolean isShow);
    }

}
