package com.example.graduation_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "positions")
public class PositionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;

    private String name;

    @OneToMany(mappedBy = "positionEntities",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EmployeeEntity> employeeEntityList;




}
