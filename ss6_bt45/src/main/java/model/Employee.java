package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Employee {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String position;
    private LocalDate birthday ;
    private double salary;
}
