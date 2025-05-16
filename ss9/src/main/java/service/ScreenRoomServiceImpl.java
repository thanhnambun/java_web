package service;
import model.ScreenRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import respository.ScreenRoomRespository;

import java.util.List;

@Service
public class ScreenRoomServiceImpl implements ScreenRoomService {

    @Autowired
    private ScreenRoomRespository screenRoomDAO;

    @Override
    public List<ScreenRoom> getAllScreenRooms() {
        return screenRoomDAO.findAllScreenRooms();
    }
}