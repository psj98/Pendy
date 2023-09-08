package com.ssafy.namani.domain.member.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
