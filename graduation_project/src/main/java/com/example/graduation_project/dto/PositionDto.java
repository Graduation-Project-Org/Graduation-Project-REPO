package com.example.graduation_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    private Long positionId;

    @NotEmpty(message = "Name ")
    private String name;
}
