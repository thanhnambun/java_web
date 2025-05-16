package service;

import model.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findAllScheduleByMovie(Long movieId);
}
