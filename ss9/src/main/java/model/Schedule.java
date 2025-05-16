package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {
    private Long id;
    private String movieTitle;
    private Date showTime;
    private Long screenRoomId;
    private Integer availableSeats;
}
