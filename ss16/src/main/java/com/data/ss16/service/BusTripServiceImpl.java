package com.data.ss16.service;

import com.data.ss16.model.BusTrip;
import com.data.ss16.responsitory.BusTripDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusTripServiceImpl implements BusTripService {
    @Autowired
    private BusTripDAO busTripDAO;

    @Override
    public void addBusTrip(BusTrip trip) {
        busTripDAO.insertBusTrip(trip);
    }
    @Override
    public List<BusTrip> findAll() {
        return busTripDAO.findAll();
    }
    @Override
    public BusTrip findById(Integer id) {
        return busTripDAO.findById(id);
    }
    @Override
    public void updateBusTrip(BusTrip trip) {
        busTripDAO.updateBusTrip(trip);
    }
    @Override
    public void deleteBusTrip(Integer id) {
        busTripDAO.deleteBusTrip(id);
    }
}