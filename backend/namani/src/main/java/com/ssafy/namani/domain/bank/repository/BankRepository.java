package com.ssafy.namani.domain.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.namani.domain.bank.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, String> {
}
