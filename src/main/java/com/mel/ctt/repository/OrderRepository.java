package com.mel.ctt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mel.ctt.domain.Client;
import com.mel.ctt.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByOrderByStatus();

	List<Order> findByClientOrderByStatus(Client client);
}
