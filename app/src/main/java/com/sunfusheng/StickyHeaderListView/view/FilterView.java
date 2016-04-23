package com.sunfusheng.StickyHeaderListView.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    @Bind(R.id.lv_left)
    ListView lvLeft;
    @Bind(R.id.lv_right)
    ListView lvRight;
    @Bind(R.id.ll_head_layout)
    LinearLayout llHeadLayout;
    @Bind(R.id.ll_content_list_view)
    LinearLayout llContentListView;
    @Bind(R.id.fl_content_view)
    FrameLayout flContentView;
    @Bind(R.id.view_mask)
    View viewMask;

    private Context mContext;
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isShowing = false;
    private int filterPosition;
    private int panelHeight;

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
        flContentView.setVisibility(GONE);
        llContentListView.setVisibility(GONE);
    }

    private void initListener() {
        llCategory.setOnClickListener(this);
        llSort.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        viewMask.setOnClickListener(this);
        llContentListView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_category:
                filterPosition = 0;
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_sort:
                filterPosition = 1;
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_filter:
                filterPosition = 2;
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.view_mask:
                resetAllStatus();
                break;
        }

    }

    // 复位筛选的显示状态
    public void resetFilterStatus() {
        tvCategory.setTextColor(mContext.getResources().getColor(R.color.font_black_2));
        ivCategoryArrow.setImageResource(R.mipmap.home_down_arrow);

        tvSort.setTextColor(mContext.getResources().getColor(R.color.font_black_2));
        ivSortArrow.setImageResource(R.mipmap.home_down_arrow);

        tvFilter.setTextColor(mContext.getResources().getColor(R.color.font_black_2));
        ivFilterArrow.setImageResource(R.mipmap.home_down_arrow);
    }

    // 复位所有的状态
    public void resetAllStatus() {
        isShowing = false;
        resetFilterStatus();
        flContentView.setVisibility(GONE);
        llContentListView.setVisibility(GONE);
    }

    // 显示动画
    public void show(Activity activity, int position) {
        resetFilterStatus();
        switch (position) {
            case 0:
                tvCategory.setTextColor(activity.getResources().getColor(R.color.orange));
                ivCategoryArrow.setImageResource(R.mipmap.home_up_arrow);
                lvLeft.setVisibility(VISIBLE);
                lvRight.setVisibility(VISIBLE);
                break;
            case 1:
                tvSort.setTextColor(activity.getResources().getColor(R.color.orange));
                ivSortArrow.setImageResource(R.mipmap.home_up_arrow);
                lvLeft.setVisibility(GONE);
                lvRight.setVisibility(VISIBLE);
                break;
            case 2:
                tvFilter.setTextColor(activity.getResources().getColor(R.color.orange));
                ivFilterArrow.setImageResource(R.mipmap.home_up_arrow);
                lvLeft.setVisibility(GONE);
                lvRight.setVisibility(VISIBLE);
                break;
        }

        if (isShowing) return ;
        isShowing = true;
        flContentView.setVisibility(VISIBLE);
        viewMask.setVisibility(VISIBLE);
        llContentListView.setVisibility(VISIBLE);
        llContentListView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llContentListView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                panelHeight = llContentListView.getHeight();
                ObjectAnimator.ofFloat(llContentListView, "translationY", -panelHeight, 0).setDuration(200).start();
            }
        });
    }

    // 隐藏动画
    public void hide() {
        flContentView.setVisibility(GONE);
        viewMask.setVisibility(View.GONE);
        llContentListView.setVisibility(GONE);
        ObjectAnimator.ofFloat(llContentListView, "translationY", 0, -panelHeight).setDuration(300).start();
    }

    public void setStickyTop(boolean stickyTop) {
        isStickyTop = stickyTop;
        if (isShowing && isStickyTop) {

        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    private OnFilterClickListener onFilterClickListener;

    public void setOnFilterClickListener(OnFilterClickListener onFilterClickListener) {
        this.onFilterClickListener = onFilterClickListener;
    }

    public interface OnFilterClickListener {
        void onFilterClick(int position);
    }

}
