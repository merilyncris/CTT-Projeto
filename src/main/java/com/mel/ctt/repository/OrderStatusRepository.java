package com.mel.ctt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mel.ctt.domain.OrderStatus;
import com.mel.ctt.domain.enums.PaymentStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

	Optional<OrderStatus> findByPaymentStatus(PaymentStatus paymentStatus);
}
