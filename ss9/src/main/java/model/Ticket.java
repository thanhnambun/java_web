package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private Long customerId;
    private Long scheduleId;
    private Long seatId;
    private Double price;
}
