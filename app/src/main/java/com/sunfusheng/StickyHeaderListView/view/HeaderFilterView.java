package com.sunfusheng.StickyHeaderListView.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.sunfusheng.StickyHeaderListView.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderFilterView extends AbsHeaderView<Object> {


    @BindView(R.id.fake_filterView)
    FilterView fakeFilterView;

    public HeaderFilterView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(Object obj, ListView listView) {
        View view = mInflate.inflate(R.layout.header_filter_layout, listView, false);
        ButterKnife.bind(this, view);
        listView.addHeaderView(view);
    }

    public FilterView getFilterView() {
        return fakeFilterView;
    }

}
