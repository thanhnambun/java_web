package com.data.ss16.service;

import com.data.ss16.model.Bus;
import com.data.ss16.responsitory.BusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusDAO busDAO;

    @Override
    public void addBus(Bus bus) {
        busDAO.insertBus(bus);
    }
    @Override
    public List<Bus> findAll() {
        return busDAO.findAll();
    }
    @Override
    public Bus findById(Integer id) {
        return busDAO.findById(id);
    }
    @Override
    public void updateBus(Bus bus) {
        busDAO.updateBus(bus);
    }
    @Override
    public void deleteBus(Integer id) {
        busDAO.deleteBus(id);
    }
}