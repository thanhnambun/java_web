package com.data.sss18_3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20, message = "firstName từ 3 đến 10 ký tự")
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 20, message = "lastName từ 3 đến 10 ký tự")
    private String lastName;
    private String phone;
    private String address;
    private String img;
    @NotBlank
    @Email
    private String email;
}
