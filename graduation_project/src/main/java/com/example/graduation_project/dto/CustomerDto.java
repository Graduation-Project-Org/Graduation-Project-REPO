package com.example.graduation_project.dto;

import com.example.graduation_project.entities.admin.AccountEntity;
import com.example.graduation_project.entities.employee.EmployeeEntity;
import com.example.graduation_project.entities.news.NewsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotBlank(message = "Tên không được bỏ trống")
    @Size(min = 2, max = 30, message = "Độ dài tên từ 2-30 ký tự")
    private String firstName;
    @NotBlank(message = "Họ không được bỏ trống")
    @Size(min = 2, max = 30, message = "Độ dài tên từ 2-30 ký tự")
    private String lastName;
    @NotNull(message = "Ngày sinh không được để trống")
    private Date dateOfBirth;
    @NotBlank(message = "Địa chỉ không được bỏ trống")
    private String address;
    @NotNull(message = "Giới tính không được để trống")
    private int gender;
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(min = 8, max = 12, message = "Độ dài tên từ 8-12 ký tự")
    @NotBlank(message = "Số cmnd không được để trống")
    private String idCard;
    private String image;
    @NotBlank(message = "email không được để trống")
    private String email;
    @NotBlank(message = "tên tài khoản không được để trống")
    private String accountName;
    @NotBlank(message = "mật khẩu không được để trống")
    private String password;
    @NotBlank(message = "mật khẩu không được để trống")
    private String confirmPassword;

}

