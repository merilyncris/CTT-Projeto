package com.mel.ctt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mel.ctt.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
