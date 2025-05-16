package service;

import model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import respository.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleDAO;

    @Override
    public List<Schedule> findAllScheduleByMovie(Long movieId) {
        return scheduleDAO.findAllScheduleByMovie(movieId);
    }
}
