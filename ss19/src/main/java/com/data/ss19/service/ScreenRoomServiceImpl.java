package com.data.ss19.service;

import com.data.ss19.model.ScreenRoom;
import com.data.ss19.model.Seat;
import com.data.ss19.repository.ScreenRoomRepository;
import com.data.ss19.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScreenRoomServiceImpl implements ScreenRoomService {
    @Autowired
    private ScreenRoomRepository screenRoomRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<ScreenRoom> findByStatus(Boolean status) {
        return screenRoomRepository.findByStatus(status);
    }

    @Override
    public ScreenRoom findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public void save(ScreenRoom screenRoom) {
        screenRoomRepository.save(screenRoom);
        createSeatsForRoom(screenRoom);
    }

    @Override
    public void update(ScreenRoom screenRoom) {
        screenRoomRepository.update(screenRoom);
    }

    @Override
    public void delete(Long id) {
        screenRoomRepository.delete(id);
    }

    @Override
    public void createSeatsForRoom(ScreenRoom screenRoom) {
        List<Seat> seats = new ArrayList<>();
        int capacity = screenRoom.getCapacity();

        int seatsPerRow = 10;
        char currentRow = 'A';

        for (int i = 1; i <= capacity; i++) {
            if (i > seatsPerRow && (i - 1) % seatsPerRow == 0) {
                currentRow++;
            }
            int seatNumber = ((i - 1) % seatsPerRow) + 1;
            String seatName = currentRow + String.valueOf(seatNumber);

            Seat seat = new Seat(seatName, screenRoom);
            seats.add(seat);
        }

        seatRepository.saveAll(seats);
    }
}