package com.his.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Description: 时间间隔
 * Date: 21-1-2
 *
 * @author yh
 */
public class LocalDateInterval implements Serializable {
    private static final long serialVersionUID = -6696237399747330194L;
    @JsonFormat(pattern = "yyyy-HH-dd")
    @DateTimeFormat(pattern = "yyyy-HH-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-HH-dd")
    @DateTimeFormat(pattern = "yyyy-HH-dd")
    private LocalDate endDate;

    public LocalDateInterval() {}

    public LocalDateInterval(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LocalDateInterval{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}