package com.data.ss10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Ticket {
    private String movieTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date showTime;
    private List<String> seats;
    private double totalAmount;

}
