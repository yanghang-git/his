package com.his.util;

import java.util.ArrayList;
import java.util.List;

public class LayuiTreeData {
    private Integer id;
    private String title;
    private String field;
    private List<LayuiTreeData> children = new ArrayList<>();
    private Boolean checked;
    private Boolean spread;

    public LayuiTreeData() {}

    public LayuiTreeData(Integer id, String title, String field) {
        this.id = id;
        this.title = title;
        this.field = field;
        this.spread = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LayuiTreeData> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTreeData> children) {
        this.children = children;
    }

    public void addChildren(LayuiTreeData data) {
        children.add(data);
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    @Override
    public String toString() {
        return "LayuiTreeData{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", children=" + children +
                ", checked=" + checked +
                ", spread=" + spread +
                '}';
    }
}
