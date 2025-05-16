package service;
import model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import respository.SeatDao;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatDao seatDAO;

    @Override
    public List<Seat> getSeatsByScreenRoom(Long screenRoomId) {
        return seatDAO.getSeatsByScreenRoom(screenRoomId);
    }

    @Override
    public List<Long> getBookedSeatsBySchedule(Long scheduleId) {
        return seatDAO.getBookedSeatsBySchedule(scheduleId);
    }
}