package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private Long id;
    private String seatNumber;
    private boolean isBooked;
}
