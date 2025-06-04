package com.data.ss19.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên ghế không được để trống")
    @Column(nullable = false)
    private String seatName;

    @Column(nullable = false)
    private Boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_room_id")
    private ScreenRoom screenRoom;

    public Seat() {}

    public Seat(String seatName, ScreenRoom screenRoom) {
        this.seatName = seatName;
        this.screenRoom = screenRoom;
        this.status = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSeatName() { return seatName; }
    public void setSeatName(String seatName) { this.seatName = seatName; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public ScreenRoom getScreenRoom() { return screenRoom; }
    public void setScreenRoom(ScreenRoom screenRoom) { this.screenRoom = screenRoom; }
}