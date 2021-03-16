package com.mel.ctt.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mel.ctt.domain.enums.PaymentStatus;
import com.mel.ctt.dto.request.OrderStatusDtoRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class OrderStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String code;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	public OrderStatus() {

	}

	public OrderStatus(OrderStatusDtoRequest orderStatusDtoRequest) {
		this.name = orderStatusDtoRequest.getName();
		this.code = orderStatusDtoRequest.getCode();
		this.paymentStatus = orderStatusDtoRequest.getPaymentStatus();
	}

}
