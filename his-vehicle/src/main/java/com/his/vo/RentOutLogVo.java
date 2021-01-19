package com.his.vo;

import com.his.pojo.RentOutLog;

import java.util.List;

public class RentOutLogVo {
    private RentOutLog rentOutLog;
    private List<String> licensePlateNumberList;
    private List<String> vehicleTypeList;
    private List<String> vehicleDescribeList;

    public RentOutLogVo(RentOutLog rentOutLog, List<String> licensePlateNumberList, List<String> vehicleTypeList, List<String> vehicleDescribeList) {
        this.rentOutLog = rentOutLog;
        this.licensePlateNumberList = licensePlateNumberList;
        this.vehicleTypeList = vehicleTypeList;
        this.vehicleDescribeList = vehicleDescribeList;
    }

    public RentOutLogVo() {
    }

    public RentOutLog getRentOutLog() {
        return rentOutLog;
    }

    public void setRentOutLog(RentOutLog rentOutLog) {
        this.rentOutLog = rentOutLog;
    }

    public List<String> getLicensePlateNumberList() {
        return licensePlateNumberList;
    }

    public void setLicensePlateNumberList(List<String> licensePlateNumberList) {
        this.licensePlateNumberList = licensePlateNumberList;
    }

    public List<String> getVehicleTypeList() {
        return vehicleTypeList;
    }

    public void setVehicleTypeList(List<String> vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }

    public List<String> getVehicleDescribeList() {
        return vehicleDescribeList;
    }

    public void setVehicleDescribeList(List<String> vehicleDescribeList) {
        this.vehicleDescribeList = vehicleDescribeList;
    }
}
