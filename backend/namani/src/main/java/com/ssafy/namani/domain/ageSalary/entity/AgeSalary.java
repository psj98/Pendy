package com.ssafy.namani.domain.ageSalary.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Builder
    public AgeSalary(Integer id, Integer age, Integer salary, Integer peopleNum) {
        this.id = id;
        this.age = age;
        this.salary = salary;
        this.peopleNum = peopleNum;
    }
}
