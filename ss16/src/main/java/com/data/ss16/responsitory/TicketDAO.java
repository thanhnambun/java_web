package com.data.ss16.responsitory;

import com.data.ss16.model.Ticket;
import com.data.ss16.utils.ConnectionDB;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDAO {

    public void insertTicket(Ticket ticket) {
        String sql = "{call insert_ticket(?,?,?,?,?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, ticket.getId());
            cs.setInt(2, ticket.getTripBusId());
            cs.setString(3, ticket.getListSeat());
            cs.setDouble(4, ticket.getTotalMoney());
            cs.setDate(5, Date.valueOf(ticket.getDepartureDate()));
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> findByUserId(Integer userId) {
        List<Ticket> list = new ArrayList<>();
        String sql = "{call get_tickets_by_user(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    list.add(mapTicket(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Ticket findById(Integer id) {
        String sql = "{call get_ticket_by_id(?)}";
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapTicket(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Ticket mapTicket(ResultSet rs) throws SQLException {
        Ticket t = new Ticket();
        t.setId(rs.getInt("id"));
        t.setUserId(rs.getInt("user_id"));
        t.setTripBusId(rs.getInt("trip_bus_id"));
        t.setListSeat(rs.getString("list_seat"));
        t.setTotalMoney(rs.getDouble("total_money"));
        t.setDepartureDate(rs.getDate("departure_date").toLocalDate());
        return t;
    }
}