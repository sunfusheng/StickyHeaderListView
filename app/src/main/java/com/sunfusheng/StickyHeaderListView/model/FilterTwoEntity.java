package com.sunfusheng.StickyHeaderListView.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunfusheng on 16/4/23.
 */
public class FilterTwoEntity implements Serializable {

    private String title;
    private List<FilterEntity> list;
    private boolean isSelected;
    private FilterEntity selectedFilterEntity;

    public FilterTwoEntity() {
    }

    public FilterTwoEntity(String title, List<FilterEntity> list) {
        this.title = title;
        this.list = list;
    }

    public FilterEntity getSelectedFilterEntity() {
        return selectedFilterEntity;
    }

    public void setSelectedFilterEntity(FilterEntity selectedFilterEntity) {
        this.selectedFilterEntity = selectedFilterEntity;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FilterEntity> getList() {
        return list;
    }

    public void setList(List<FilterEntity> list) {
        this.list = list;
    }
}
