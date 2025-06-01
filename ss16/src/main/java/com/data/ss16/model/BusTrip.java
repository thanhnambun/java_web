package com.data.ss16.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class BusTrip {
    private Integer id;

    @NotBlank(message = "Điểm đi không được để trống")
    private String departurePoint;

    @NotBlank(message = "Điểm đến không được để trống")
    private String destination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime departureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime arrivalTime;


    @NotNull(message = "Chưa chọn xe")
    private Integer busId;

    @NotNull(message = "Số ghế trống không được để trống")
    private Integer seatsAvailable;

    private String image;

    public Integer getId() {
        return id;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getBusId() {
        return busId;
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public String getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setImage(String image) {
        this.image = image;
    }
}