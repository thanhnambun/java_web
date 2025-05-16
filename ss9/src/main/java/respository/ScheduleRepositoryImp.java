package respository;

import model.Schedule;
import org.springframework.stereotype.Repository;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ScheduleRepositoryImp implements ScheduleRepository {
    @Override
    public List<Schedule> findAllScheduleByMovie(Long movieId) {
        List<Schedule> list = new ArrayList<>();
        String sql = "{CALL get_schedules_by_movie(?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setLong(1, movieId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getLong("id"));
                schedule.setMovieTitle(rs.getString("movieTitle"));
                schedule.setScreenRoomId(rs.getLong("screen_room_id"));
                schedule.setShowTime(new java.sql.Date(rs.getTimestamp("showTime").getTime()));
                schedule.setAvailableSeats(rs.getInt("availableSeats"));
                list.add(schedule);
            }
            ConnectionDB.closeConnection(conn,stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
