package com.data.ss16.service;

import com.data.ss16.model.Bus;

import java.util.List;

public interface BusService {
    void addBus(Bus bus);
    List<Bus> findAll();
    Bus findById(Integer id);
    void updateBus(Bus bus);
    void deleteBus(Integer id);
}