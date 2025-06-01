package com.data.ss16.service;

import com.data.ss16.model.BusTrip;

import java.util.List;

public interface BusTripService {
    void addBusTrip(BusTrip trip);
    List<BusTrip> findAll();
    BusTrip findById(Integer id);
    void updateBusTrip(BusTrip trip);
    void deleteBusTrip(Integer id);
}
