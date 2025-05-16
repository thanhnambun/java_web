package respository;

import model.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAllScheduleByMovie(Long movieId);
}
