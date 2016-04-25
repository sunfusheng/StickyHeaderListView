package com.sunfusheng.StickyHeaderListView.model;

import java.util.Comparator;

/**
 * Created by sunfusheng on 16/4/25.
 */
public class TravelingEntityComparator implements Comparator<TravelingEntity> {

    @Override
    public int compare(TravelingEntity lhs, TravelingEntity rhs) {
        return rhs.getRank() - lhs.getRank();
    }
}
