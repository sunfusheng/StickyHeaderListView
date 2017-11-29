package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sunfusheng.StickyHeaderListView.R;
import com.sunfusheng.StickyHeaderListView.adapter.HeaderBannerAdapter;
import com.sunfusheng.StickyHeaderListView.util.DensityUtil;
import com.sunfusheng.glideimageview.GlideImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderBannerView extends AbsHeaderView<List<String>> {

    @BindView(R.id.vp_banner)
    ViewPager vpBanner;
    @BindView(R.id.ll_index_container)
    LinearLayout llIndexContainer;
    @BindView(R.id.rl_banner)
    RelativeLayout rlBanner;

    private static final int BANNER_TYPE = 0;
    private static final int BANNER_TIME = 5000; // ms
    private List<ImageView> ivList;
    private int bannerHeight;
    private int bannerCount;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == BANNER_TYPE) {
                vpBanner.setCurrentItem(vpBanner.getCurrentItem() + 1);
                enqueueBannerLoopMessage();
            }
        }
    };

    public HeaderBannerView(Activity context) {
        super(context);
        ivList = new ArrayList<>();
        bannerHeight = DensityUtil.getWindowWidth(context) * 9 / 16;
    }

    @Override
    protected void getView(List<String> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_banner_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<String> list) {
        ivList.clear();

        bannerCount = list.size();
        if (bannerCount == 2) {
            list.addAll(list);
        }

        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) rlBanner.getLayoutParams();
        layoutParams.height = bannerHeight;
        rlBanner.setLayoutParams(layoutParams);

        createImageViews(list);

        HeaderBannerAdapter adapter = new HeaderBannerAdapter(ivList);
        vpBanner.setAdapter(adapter);

        addIndicatorImageViews();
        setViewPagerChangeListener();
        controlViewPagerSpeed(vpBanner, 500);
    }

    // 创建要显示的ImageView
    private void createImageViews(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            GlideImageView imageView = new GlideImageView(mActivity);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.loadImage(list.get(i), R.color.font_black_6);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ivList.add(imageView);
        }
    }

    // 添加指示图标
    private void addIndicatorImageViews() {
        llIndexContainer.removeAllViews();
        if (bannerCount < 2) return;
        for (int i = 0; i < bannerCount; i++) {
            ImageView iv = new ImageView(mActivity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(mActivity, 5), DensityUtil.dip2px(mActivity, 5));
            lp.leftMargin = DensityUtil.dip2px(mActivity, (i == 0) ? 0 : 7);
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(R.drawable.xml_round_orange_grey_sel);
            iv.setEnabled(i == 0);
            llIndexContainer.addView(iv);
        }
    }

    // 为ViewPager设置监听器
    private void setViewPagerChangeListener() {
        vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (ivList == null || ivList.size() == 0) return;
                int newPosition = position % bannerCount;
                for (int i = 0; i < bannerCount; i++) {
                    llIndexContainer.getChildAt(i).setEnabled(i == newPosition);
                }
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    // 添加Banner循环消息到队列
    public void enqueueBannerLoopMessage() {
        if (ivList == null || ivList.size() <= 1) return;
        mHandler.sendEmptyMessageDelayed(BANNER_TYPE, BANNER_TIME);
    }

    // 移除Banner循环的消息
    public void removeBannerLoopMessage() {
        if (mHandler.hasMessages(BANNER_TYPE)) {
            mHandler.removeMessages(BANNER_TYPE);
        }
    }

    // 反射设置ViewPager的轮播速度
    private void controlViewPagerSpeed(ViewPager viewPager, int speedTimeMillis) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mActivity, new AccelerateDecelerateInterpolator());
            scroller.setDuration(speedTimeMillis);
            field.set(viewPager, scroller);
        } catch (Exception e) {
        }
    }

}
