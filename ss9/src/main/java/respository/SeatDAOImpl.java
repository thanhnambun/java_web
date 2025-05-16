package respository;

import model.Seat;
import org.springframework.stereotype.Repository;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatDAOImpl implements SeatDao {

    @Override
    public List<Seat> getSeatsByScreenRoom(Long screenRoomId) {
        List<Seat> seats = new ArrayList<>();
        String sql = "{CALL get_seats_by_screen_room(?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, screenRoomId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getLong("id"));
                seat.setSeatNumber(rs.getString("seat_number"));
                seat.setBooked(rs.getBoolean("is_booked"));
                seats.add(seat);
            }
            ConnectionDB.closeConnection(conn,stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    @Override
    public List<Long> getBookedSeatsBySchedule(Long scheduleId) {
        List<Long> bookedSeatIds = new ArrayList<>();
        String sql = "{CALL get_booked_seats_by_schedule(?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookedSeatIds.add(rs.getLong("seat_id"));
            }
            ConnectionDB.closeConnection(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedSeatIds;
    }
}
