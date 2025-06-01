package com.data.ss16.responsitory;


import com.data.ss16.model.BusTrip;
import com.data.ss16.utils.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusTripDAO {

    public void insertBusTrip(BusTrip trip) {
        String sql = "{call insert_bustrip(?,?,?,?,?,?,?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, trip.getDeparturePoint());
            cs.setString(2, trip.getDestination());
            cs.setTimestamp(3, Timestamp.valueOf(trip.getDepartureTime()));
            cs.setTimestamp(4, Timestamp.valueOf(trip.getArrivalTime()));
            cs.setInt(5, trip.getBusId());
            cs.setInt(6, trip.getSeatsAvailable());
            cs.setString(7, trip.getImage());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BusTrip> findAll() {
        List<BusTrip> list = new ArrayList<>();
        String sql = "{call get_all_bustrip()}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                list.add(mapTrip(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public BusTrip findById(Integer id) {
        String sql = "{call get_bustrip_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapTrip(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBusTrip(BusTrip trip) {
        String sql = "{call update_bustrip(?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, trip.getId());
            cs.setString(2, trip.getDeparturePoint());
            cs.setString(3, trip.getDestination());
            cs.setTimestamp(4, Timestamp.valueOf(trip.getDepartureTime()));
            cs.setTimestamp(5, Timestamp.valueOf(trip.getArrivalTime()));
            cs.setInt(6, trip.getBusId());
            cs.setInt(7, trip.getSeatsAvailable());
            cs.setString(8, trip.getImage());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBusTrip(Integer id) {
        String sql = "{call delete_bustrip(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BusTrip mapTrip(ResultSet rs) throws SQLException {
        BusTrip b = new BusTrip();
        b.setId(rs.getInt("id"));
        b.setDeparturePoint(rs.getString("departure_point"));
        b.setDestination(rs.getString("destination"));
        b.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
        b.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
        b.setBusId(rs.getInt("bus_id"));
        b.setSeatsAvailable(rs.getInt("seats_available"));
        b.setImage(rs.getString("image"));
        return b;
    }
}