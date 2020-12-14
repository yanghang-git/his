package com.his.pojo;


import java.io.Serializable;
import java.util.Comparator;

public class KpPermission implements Serializable, Comparable<KpPermission> {

  private static final long serialVersionUID = -3820220161082018713L;
  private Integer permId;
  private String permName;
  private String permCode;
  private String permUrl;
  private Integer parentId;
  private Integer sort;

  public KpPermission() {}

  public KpPermission(Integer permId, String permName, String permCode, String permUrl, Integer parentId, Integer sort) {
    this.permId = permId;
    this.permName = permName;
    this.permCode = permCode;
    this.permUrl = permUrl;
    this.parentId = parentId;
    this.sort = sort;
  }

  public Integer getPermId() {
    return permId;
  }

  public void setPermId(Integer permId) {
    this.permId = permId;
  }


  public String getPermName() {
    return permName;
  }

  public void setPermName(String permName) {
    this.permName = permName;
  }


  public String getPermCode() {
    return permCode;
  }

  public void setPermCode(String permCode) {
    this.permCode = permCode;
  }


  public String getPermUrl() {
    return permUrl;
  }

  public void setPermUrl(String permUrl) {
    this.permUrl = permUrl;
  }


  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }


  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  @Override
  public String toString() {
    return "KpPermission{" +
            "permId=" + permId +
            ", permName='" + permName + '\'' +
            ", permCode=" + permCode +
            ", permUrl='" + permUrl + '\'' +
            ", parentId=" + parentId +
            ", sort=" + sort +
            '}';
  }


  @Override
  public int compareTo(KpPermission o) {
    return this.getSort() - o.getSort();
  }
}