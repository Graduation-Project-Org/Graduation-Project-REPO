package com.example.graduation_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {


    private Long roleId;

    private String name;

    private String url;

    private String code;

    private Boolean isEdit=false;
}
