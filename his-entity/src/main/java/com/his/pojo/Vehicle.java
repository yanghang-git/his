package com.his.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Vehicle implements Serializable {

  private static final long serialVersionUID = -2282281600210912016L;
  private String licensePlateNumber;
  private String vehicleIdNumber;
  private Integer vehicleType;
  private String vehicleColor;
  private Double vehiclePrice;
  private Double rentOut;
  private Double rentalDeposit;
  private Boolean vehicleState;
  private String vehicleDescribe;
  private String vehicleImg;
  @JsonFormat(pattern = "yyyy-MM-dd mm:hh")
  @DateTimeFormat(pattern = "yyyy-MM-dd mm:hh")
  private java.time.LocalDateTime time;
  private Integer shop;
  private Integer operator;

  public Vehicle() {}

  public Vehicle(String licensePlateNumber, String vehicleIdNumber, Integer vehicleType, String vehicleColor, Double vehiclePrice, Double rentOut, Double rentalDeposit, Boolean vehicleState, String vehicleDescribe, String vehicleImg, LocalDateTime time, Integer shop, Integer operator) {
    this.licensePlateNumber = licensePlateNumber;
    this.vehicleIdNumber = vehicleIdNumber;
    this.vehicleType = vehicleType;
    this.vehicleColor = vehicleColor;
    this.vehiclePrice = vehiclePrice;
    this.rentOut = rentOut;
    this.rentalDeposit = rentalDeposit;
    this.vehicleState = vehicleState;
    this.vehicleDescribe = vehicleDescribe;
    this.vehicleImg = vehicleImg;
    this.time = time;
    this.shop = shop;
    this.operator = operator;
  }

  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

  public void setLicensePlateNumber(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }


  public String getVehicleIdNumber() {
    return vehicleIdNumber;
  }

  public void setVehicleIdNumber(String vehicleIdNumber) {
    this.vehicleIdNumber = vehicleIdNumber;
  }


  public Integer getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(Integer vehicleType) {
    this.vehicleType = vehicleType;
  }


  public String getVehicleColor() {
    return vehicleColor;
  }

  public void setVehicleColor(String vehicleColor) {
    this.vehicleColor = vehicleColor;
  }


  public Double getVehiclePrice() {
    return vehiclePrice;
  }

  public void setVehiclePrice(Double vehiclePrice) {
    this.vehiclePrice = vehiclePrice;
  }


  public Double getRentOut() {
    return rentOut;
  }

  public void setRentOut(Double rentOut) {
    this.rentOut = rentOut;
  }


  public Double getRentalDeposit() {
    return rentalDeposit;
  }

  public void setRentalDeposit(Double rentalDeposit) {
    this.rentalDeposit = rentalDeposit;
  }


  public Boolean getVehicleState() {
    return vehicleState;
  }

  public void setVehicleState(Boolean vehicleState) {
    this.vehicleState = vehicleState;
  }


  public String getVehicleDescribe() {
    return vehicleDescribe;
  }

  public void setVehicleDescribe(String vehicleDescribe) {
    this.vehicleDescribe = vehicleDescribe;
  }


  public String getVehicleImg() {
    return vehicleImg;
  }

  public void setVehicleImg(String vehicleImg) {
    this.vehicleImg = vehicleImg;
  }


  public java.time.LocalDateTime getTime() {
    return time;
  }

  public void setTime(java.time.LocalDateTime time) {
    this.time = time;
  }


  public Integer getShop() {
    return shop;
  }

  public void setShop(Integer shop) {
    this.shop = shop;
  }


  public Integer getOperator() {
    return operator;
  }

  public void setOperator(Integer operator) {
    this.operator = operator;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
            "licensePlateNumber='" + licensePlateNumber + '\'' +
            ", vehicleIdNumber='" + vehicleIdNumber + '\'' +
            ", vehicleType=" + vehicleType +
            ", vehicleColor='" + vehicleColor + '\'' +
            ", vehiclePrice=" + vehiclePrice +
            ", rentOut=" + rentOut +
            ", rentalDeposit=" + rentalDeposit +
            ", vehicleState='" + vehicleState + '\'' +
            ", vehicleDescribe='" + vehicleDescribe + '\'' +
            ", vehicleImg='" + vehicleImg + '\'' +
            ", time=" + time +
            ", shop=" + shop +
            ", operator=" + operator +
            '}';
  }
}
