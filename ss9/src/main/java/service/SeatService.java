package service;

import model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getSeatsByScreenRoom(Long screenRoomId);
    List<Long> getBookedSeatsBySchedule(Long scheduleId);
}
