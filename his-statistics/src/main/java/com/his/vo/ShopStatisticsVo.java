package com.his.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description: TO DO
 * Date: 21-1-20
 *
 * @author yh
 */
public class ShopStatisticsVo implements Serializable {
    private static final long serialVersionUID = 5980362332030198301L;
    private Integer shopId;
    private String shopName;
    private String shopAddress;
    private List<String> managers;
    private Integer rentOutSize;
    private Double rentOutTotalMoney;
    private Integer newClientSize;

    public ShopStatisticsVo() {
        this.managers = new ArrayList<>();
        this.rentOutSize = 0;
        this.rentOutTotalMoney = 0.0;
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

    public List<String> getManagers() {
        return managers;
    }

    public void setManagers(List<String> managers) {
        this.managers = managers;
    }

    public Integer getRentOutSize() {
        return rentOutSize;
    }

    public void setRentOutSize(Integer rentOutSize) {
        this.rentOutSize = rentOutSize;
    }

    public Double getRentOutTotalMoney() {
        return rentOutTotalMoney;
    }

    public void setRentOutTotalMoney(Double rentOutTotalMoney) {
        this.rentOutTotalMoney = rentOutTotalMoney;
    }

    public Integer getNewClientSize() {
        return newClientSize;
    }

    public void setNewClientSize(Integer newClientSize) {
        this.newClientSize = newClientSize;
    }

    @Override
    public String toString() {
        return "ShopStatisticsVo{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", managers=" + managers +
                ", rentOutSize=" + rentOutSize +
                ", rentOutTotalMoney=" + rentOutTotalMoney +
                ", newClientSize=" + newClientSize +
                '}';
    }
}