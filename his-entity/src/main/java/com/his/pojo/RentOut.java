package com.his.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RentOut implements Serializable {

  private static final long serialVersionUID = -103408430438119117L;
  private String oddNumbers;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 
  private LocalDateTime ofTheTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") 
  private LocalDateTime predictReturnTime;
  private String clientId;
  private Double rentOutTotalMoney;
  private Boolean isPickUp;
  private String comment;


  public RentOut() { }

  public RentOut(String oddNumbers, LocalDateTime ofTheTime, LocalDateTime predictReturnTime, String clientId, Double rentOutTotalMoney, Boolean isPickUp, String comment) {
    this.oddNumbers = oddNumbers;
    this.ofTheTime = ofTheTime;
    this.predictReturnTime = predictReturnTime;
    this.clientId = clientId;
    this.rentOutTotalMoney = rentOutTotalMoney;
    this.isPickUp = isPickUp;
    this.comment = comment;
  }

  public String getOddNumbers() {
    return oddNumbers;
  }

  public void setOddNumbers(String oddNumbers) {
    this.oddNumbers = oddNumbers;
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


  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }


  public Double getRentOutTotalMoney() {
    return rentOutTotalMoney;
  }

  public void setRentOutTotalMoney(Double rentOutTotalMoney) {
    this.rentOutTotalMoney = rentOutTotalMoney;
  }


  public Boolean getIsPickUp() {
    return isPickUp;
  }

  public void setIsPickUp(Boolean isPickUp) {
    this.isPickUp = isPickUp;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "RentOut{" +
            "oddNumbers='" + oddNumbers + '\'' +
            ", ofTheTime=" + ofTheTime +
            ", predictReturnTime=" + predictReturnTime +
            ", clientId=" + clientId +
            ", rentOutTotalMoney=" + rentOutTotalMoney +
            ", isPickUp=" + isPickUp +
            ", comment='" + comment + '\'' +
            '}';
  }
}
