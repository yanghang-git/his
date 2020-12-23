package com.his.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

public class Shop implements Serializable {

  private static final long serialVersionUID = -6498511895327444772L;
  @TableId(type = IdType.AUTO)
  private Integer shopId;
  private String shopName;
  private String shopAddress;

  public Shop() {}

  public Shop(Integer shopId, String shopName, String shopAddress) {
    this.shopId = shopId;
    this.shopName = shopName;
    this.shopAddress = shopAddress;
  }

  public Integer getShopId() {
    return shopId;
  }

  public void setShopId(Integer shopId) {
    this.shopId = shopId;
  }


  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }


  public String getShopAddress() {
    return shopAddress;
  }

  public void setShopAddress(String shopAddress) {
    this.shopAddress = shopAddress;
  }

  @Override
  public String toString() {
    return "Shop{" +
            "shopId=" + shopId +
            ", shopName='" + shopName + '\'' +
            ", shopAddress='" + shopAddress + '\'' +
            '}';
  }
}
