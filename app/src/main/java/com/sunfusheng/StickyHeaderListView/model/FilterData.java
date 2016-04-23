package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunfusheng on 16/4/23.
 */
public class FilterData implements Serializable {

    private List<FilterTwoEntity> category;
    private List<FilterEntity> sorts;
    private List<FilterEntity> filters;

    public List<FilterTwoEntity> getCategory() {
        return category;
    }

    public void setCategory(List<FilterTwoEntity> category) {
        this.category = category;
    }

    public List<FilterEntity> getSorts() {
        return sorts;
    }

    public void setSorts(List<FilterEntity> sorts) {
        this.sorts = sorts;
    }

    public List<FilterEntity> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterEntity> filters) {
        this.filters = filters;
    }
}
