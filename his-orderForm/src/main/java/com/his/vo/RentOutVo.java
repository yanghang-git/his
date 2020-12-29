package com.his.vo;

import com.his.pojo.Client;
import com.his.pojo.RentOut;
import com.his.pojo.RentOutVehicle;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-28
 *
 * @author yh
 */
public class RentOutVo {
    private Client client;
    private RentOut rentOut;
    private List<RentOutVehicle> rentOutVehicleList;
    private Boolean isAdd;
    private Boolean isUpdate;

    public RentOutVo() {}

    public RentOutVo(Client client, RentOut rentOut, List<RentOutVehicle> rentOutVehicleList, Boolean isAdd, Boolean isUpdate) {
        this.client = client;
        this.rentOut = rentOut;
        this.rentOutVehicleList = rentOutVehicleList;
        this.isAdd = isAdd;
        this.isUpdate = isUpdate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RentOut getRentOut() {
        return rentOut;
    }

    public void setRentOut(RentOut rentOut) {
        this.rentOut = rentOut;
    }

    public List<RentOutVehicle> getRentOutVehicleList() {
        return rentOutVehicleList;
    }

    public void setRentOutVehicleList(List<RentOutVehicle> rentOutVehicleList) {
        this.rentOutVehicleList = rentOutVehicleList;
    }

    public Boolean getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Boolean add) {
        isAdd = add;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean update) {
        isUpdate = update;
    }

    @Override
    public String toString() {
        return "RentOutVo{" +
                "client=" + client +
                ", rentOut=" + rentOut +
                ", rentOutVehicleList=" + rentOutVehicleList +
                ", isAdd=" + isAdd +
                ", isUpdate=" + isUpdate +
                '}';
    }
}