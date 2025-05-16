package respository;

import model.Seat;

import java.util.List;

public interface SeatDao {
    List<Seat> getSeatsByScreenRoom(Long screenRoomId);
    List<Long> getBookedSeatsBySchedule(Long scheduleId);
}
