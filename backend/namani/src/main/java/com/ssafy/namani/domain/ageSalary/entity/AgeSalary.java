package com.ssafy.namani.domain.ageSalary.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class AgeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 나이, 소득 구간 아이디

    @NotNull
    private Integer age; // 연령대

    @NotNull
    private Integer salary; // 연봉대

    @NotNull
    private Integer peopleNum; // 인원수
}
