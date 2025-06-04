package com.data.ss19.model;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theater_id;

    @NotBlank(message = "Tên rạp không được để trống")
    @Column(nullable = false)
    private String theaterName;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Column(nullable = false)
    private String address;

    @Min(value = 1, message = "Số phòng chiếu phải lớn hơn 0")
    private Integer numberScreenRoom;

    @Column(nullable = false)
    private Boolean status = true;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScreenRoom> screenRooms;

    public Theater() {}

    public Long getTheater_id() { return theater_id; }
    public void setTheater_id(Long id) { this.theater_id = id; }

    public String getTheaterName() { return theaterName; }
    public void setTheaterName(String theaterName) { this.theaterName = theaterName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getNumberScreenRoom() { return numberScreenRoom; }
    public void setNumberScreenRoom(Integer numberScreenRoom) { this.numberScreenRoom = numberScreenRoom; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public List<ScreenRoom> getScreenRooms() { return screenRooms; }
    public void setScreenRooms(List<ScreenRoom> screenRooms) { this.screenRooms = screenRooms; }
}