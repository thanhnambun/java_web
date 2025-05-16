package respository;

import model.ScreenRoom;
import org.springframework.stereotype.Repository;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ScreenRoomRespositoryImp implements ScreenRoomRespository {
    @Override
    public List<ScreenRoom> findAllScreenRooms() {
        List<ScreenRoom> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall("{call get_all_screen_rooms()}");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ScreenRoom sr = new ScreenRoom();
                sr.setId(rs.getLong("id"));
                sr.setScreenRoomName(rs.getString("screenRoomName"));
                sr.setTotalSeat(rs.getInt("totalSeat"));
                list.add(sr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
