package com.mel.ctt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mel.ctt.domain.Order;
import com.mel.ctt.domain.Payment;
import com.mel.ctt.domain.enums.PaymentStatus;
import com.mel.ctt.exception.NotFound;
import com.mel.ctt.repository.OrderRepository;
import com.mel.ctt.repository.OrderStatusRepository;
import com.mel.ctt.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired OrderStatusRepository orderStatusRepository;
	
	public Payment findById(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new NotFound());
		return payment;
	}
	
	public void updateStatus(PaymentStatus status, Long id) {
		Payment payment = this.findById(id);
		payment.setStatus(status);
		paymentRepository.saveAndFlush(payment);
		
		Order order = orderRepository.findById(id).orElseThrow(() -> new NotFound());
					
		order.setStatus(orderStatusRepository.findByPaymentStatus(order.getPayment().getStatus()).orElseThrow(() -> new NotFound()));
		orderRepository.save(order);
		
	}
	
}
