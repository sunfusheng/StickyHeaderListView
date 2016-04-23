package com.sunfusheng.StickyHeaderListView.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.sunfusheng.StickyHeaderListView.R;


/**
 * Created by sunfusheng on 16/1/25.
 */
public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    // 滑动到最下面时的上拉操作
    private int mTouchSlop;

    // ListView实例
    private ListView mListView;

    // 上拉监听器, 到了最底部的上拉加载操作
    private OnLoadListener mOnLoadListener;

    // ListView的加载中footer
    private View mListViewFooter;

    // 按下时的y坐标
    private int mYDown;

    // 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
    private int mLastY;

    // 是否在加载中 ( 上拉加载更多 )
    private boolean isLoading = false;

    // 是否可以上拉加载更多
    private boolean canLoad = true;

    // 是否显示加载更多的Footer View
    private boolean haveLoadingView = true;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.refresh_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // 初始化ListView对象
        if (mListView == null) {
            getListView();
        }
    }

    // 获取ListView对象
    private void getListView() {
        int childes = getChildCount();
        if (childes > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                mListView.setOnScrollListener(this);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                // 抬起
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    // 是否可以加载更多, 条件是到了最底部, ListView不在加载中, 且为上拉操作
    private boolean canLoad() {
        return canLoad && isBottom() && !isLoading && isPullUp();
    }

    // 判断是否到了最底部
    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 3);
        }
        return false;
    }

    // 是否是上拉操作
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    // 如果到了最底部, 而且是上拉操作, 那么执行onLoad方法
    private void loadData() {
        if (mOnLoadListener != null) {
            // 设置状态
            setLoading(true);
            mOnLoadListener.onLoad();
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            if (haveLoadingView) {
                mListView.addFooterView(mListViewFooter);
            }
        } else {
            if (haveLoadingView) {
                mListView.removeFooterView(mListViewFooter);
            }
            mYDown = 0;
            mLastY = 0;
        }
    }

    // 是否可以上拉加载更多
    public void setCanLoad(boolean canLoad) {
        this.canLoad = canLoad;
    }

    // 是否显示加载更多的Footer View
    public void setHaveLoadingView(boolean haveLoadingView) {
        this.haveLoadingView = haveLoadingView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 滚动时到了最底部也可以加载更多
        if (canLoad()) {
            loadData();
        }
    }

    // 加载更多的监听器
    public interface OnLoadListener {
        void onLoad();
    }
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

}
