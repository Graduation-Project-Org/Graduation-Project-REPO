package com.example.graduation_project.dto;

import com.example.graduation_project.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    private Long positionId;

    @NotEmpty(message = "Name ")
    private String name;
}
