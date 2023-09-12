package com.ssafy.namani.domain.member.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id; // UUID 식별자

    @NotNull
    private String email; // 이메일

    @NotNull
    private String password; // 비밀번호

    @NotNull
    private String name; // 이름

    @NotNull
    private Integer age; // 나이

    @NotNull
    private Integer salary; // 연봉

    @Builder
    public Member(UUID id, String email, String password, String name, Integer age, Integer salary) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
