package com.data.ss16.responsitory;

import com.data.ss16.model.Bus;
import com.data.ss16.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusDAO {

    public void insertBus(Bus bus) {
        String sql = "{call insert_bus(?,?,?,?,?,?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, bus.getLicensePlate());
            cs.setString(2, bus.getBusType());
            cs.setInt(3, bus.getRowSeat());
            cs.setInt(4, bus.getColSeat());
            cs.setInt(5, bus.getTotalSeat());
            cs.setString(6, bus.getImage());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bus> findAll() {
        List<Bus> list = new ArrayList<>();
        String sql = "{call get_all_bus()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getInt("id"));
                bus.setLicensePlate(rs.getString("license_plate"));
                bus.setBusType(rs.getString("bus_type"));
                bus.setRowSeat(rs.getInt("row_seat"));
                bus.setColSeat(rs.getInt("col_seat"));
                bus.setTotalSeat(rs.getInt("total_seat"));
                bus.setImage(rs.getString("image"));
                list.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Bus findById(Integer id) {
        String sql = "{call get_bus_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    Bus bus = new Bus();
                    bus.setId(rs.getInt("id"));
                    bus.setLicensePlate(rs.getString("license_plate"));
                    bus.setBusType(rs.getString("bus_type"));
                    bus.setRowSeat(rs.getInt("row_seat"));
                    bus.setColSeat(rs.getInt("col_seat"));
                    bus.setTotalSeat(rs.getInt("total_seat"));
                    bus.setImage(rs.getString("image"));
                    return bus;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBus(Bus bus) {
        String sql = "{call update_bus(?,?,?,?,?,?,?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, bus.getId());
            cs.setString(2, bus.getLicensePlate());
            cs.setString(3, bus.getBusType());
            cs.setInt(4, bus.getRowSeat());
            cs.setInt(5, bus.getColSeat());
            cs.setInt(6, bus.getTotalSeat());
            cs.setString(7, bus.getImage());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBus(Integer id) {
        String sql = "{call delete_bus(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}