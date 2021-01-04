package com.his.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


public class RentOutLog implements Serializable, Cloneable {
  private static final long serialVersionUID = 2364877692974204517L;

  @TableId(type = IdType.INPUT)
  private String logNumbers;
  @JsonFormat(pattern = "yyyy-MM-dd mm:hh")
  @DateTimeFormat(pattern = "yyyy-MM-dd mm:hh")
  private LocalDateTime ofTheTime;
  @JsonFormat(pattern = "yyyy-MM-dd mm:hh")
  @DateTimeFormat(pattern = "yyyy-MM-dd mm:hh")
  private LocalDateTime predictReturnTime;
  private String predictRentOut;
  @JsonFormat(pattern = "yyyy-MM-dd mm:hh")
  @DateTimeFormat(pattern = "yyyy-MM-dd mm:hh")
  private LocalDateTime actualReturnTime;
  private double actualCollectionMoney;
  private String licensePlateNumber;
  private String vehicleType;
  private String clientName;
  private String clientPhone;
  private Boolean clientSex;
  private String existingProblem;
  private Integer logShop;
  private double compensatePrice;
  private String operator;
  private String vehicleDesc;

  public RentOutLog() {
  }


  public RentOutLog(String logNumbers, LocalDateTime ofTheTime, LocalDateTime predictReturnTime, String predictRentOut, LocalDateTime actualReturnTime, double actualCollectionMoney, String licensePlateNumber, String vehicleType, String clientName, String clientPhone, Boolean clientSex, String existingProblem, Integer logShop, double compensatePrice, String operator, String vehicleDesc) {
    this.logNumbers = logNumbers;
    this.ofTheTime = ofTheTime;
    this.predictReturnTime = predictReturnTime;
    this.predictRentOut = predictRentOut;
    this.actualReturnTime = actualReturnTime;
    this.actualCollectionMoney = actualCollectionMoney;
    this.licensePlateNumber = licensePlateNumber;
    this.vehicleType = vehicleType;
    this.clientName = clientName;
    this.clientPhone = clientPhone;
    this.clientSex = clientSex;
    this.existingProblem = existingProblem;
    this.logShop = logShop;
    this.compensatePrice = compensatePrice;
    this.operator = operator;
    this.vehicleDesc = vehicleDesc;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getLogNumbers() {
    return logNumbers;
  }

  public void setLogNumbers(String logNumbers) {
    this.logNumbers = logNumbers;
  }


  public LocalDateTime getOfTheTime() {
    return ofTheTime;
  }

  public void setOfTheTime(LocalDateTime ofTheTime) {
    this.ofTheTime = ofTheTime;
  }


  public LocalDateTime getPredictReturnTime() {
    return predictReturnTime;
  }

  public void setPredictReturnTime(LocalDateTime predictReturnTime) {
    this.predictReturnTime = predictReturnTime;
  }


  public String getPredictRentOut() {
    return predictRentOut;
  }

  public void setPredictRentOut(String predictRentOut) {
    this.predictRentOut = predictRentOut;
  }


  public LocalDateTime getActualReturnTime() {
    return actualReturnTime;
  }

  public void setActualReturnTime(LocalDateTime actualReturnTime) {
    this.actualReturnTime = actualReturnTime;
  }


  public double getActualCollectionMoney() {
    return actualCollectionMoney;
  }

  public void setActualCollectionMoney(double actualCollectionMoney) {
    this.actualCollectionMoney = actualCollectionMoney;
  }


  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

  public void setLicensePlateNumber(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }


  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }


  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }


  public Boolean getClientSex() { return clientSex; }

  public void setClientSex(Boolean clientSex) { this.clientSex = clientSex; }


  public String getExistingProblem() {
    return existingProblem;
  }

  public void setExistingProblem(String existingProblem) {
    this.existingProblem = existingProblem;
  }

  public Integer getLogShop() {
    return logShop;
  }

  public void setLogShop(Integer logShop) {
    this.logShop = logShop;
  }

  public double getCompensatePrice() {
    return compensatePrice;
  }

  public void setCompensatePrice(double compensatePrice) {
    this.compensatePrice = compensatePrice;
  }

  public String getVehicleDesc() {
    return vehicleDesc;
  }

  public void setVehicleDesc(String vehicleDesc) {
    this.vehicleDesc = vehicleDesc;
  }


  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }


  @Override
  public String toString() {
    return "RentOutLog{" +
            "logNumbers='" + logNumbers + '\'' +
            ", ofTheTime=" + ofTheTime +
            ", predictReturnTime=" + predictReturnTime +
            ", predictRentOut='" + predictRentOut + '\'' +
            ", actualReturnTime=" + actualReturnTime +
            ", actualCollectionMoney=" + actualCollectionMoney +
            ", licensePlateNumber='" + licensePlateNumber + '\'' +
            ", vehicleType='" + vehicleType + '\'' +
            ", clientName='" + clientName + '\'' +
            ", clientPhone='" + clientPhone + '\'' +
            ", clientSex=" + clientSex +
            ", existingProblem='" + existingProblem + '\'' +
            ", logShop=" + logShop +
            ", compensatePrice=" + compensatePrice +
            ", operator='" + operator + '\'' +
            ", vehicleDesc='" + vehicleDesc + '\'' +
            '}';
  }
}
