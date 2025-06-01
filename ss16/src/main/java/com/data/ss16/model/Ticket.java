package com.data.ss16.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Ticket {
    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer tripBusId;

    @NotBlank(message = "Chưa chọn ghế")
    private String listSeat;

    @NotNull
    private Double totalMoney;

    @NotNull(message = "Chưa chọn ngày đi")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate departureDate;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getTripBusId() {
        return tripBusId;
    }

    public String getListSeat() {
        return listSeat;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTripBusId(Integer tripBusId) {
        this.tripBusId = tripBusId;
    }

    public void setListSeat(String listSeat) {
        this.listSeat = listSeat;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}