package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunfusheng on 16/4/23.
 */
public class FilterTwoEntity implements Serializable {

    private String type;
    private List<FilterEntity> list;
    private boolean isSelected;

    public FilterTwoEntity() {
    }

    public FilterTwoEntity(String type, List<FilterEntity> list) {
        this.type = type;
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<FilterEntity> getList() {
        return list;
    }

    public void setList(List<FilterEntity> list) {
        this.list = list;
    }
}
