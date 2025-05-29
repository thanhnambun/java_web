package com.data.hkt.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeModel {

    private int employeeId;

    @NotBlank(message = "Tên nhân viên không được để trống")
    @Size(min = 2, max = 100, message = "Tên phải từ 2 đến 100 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0[1-9][0-9]{8,9}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    private String avatarUrl;

    private Boolean status;
    private Date created_at;

    private int departmentId;


}
