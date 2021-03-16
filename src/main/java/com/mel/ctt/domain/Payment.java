package com.mel.ctt.domain;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mel.ctt.domain.enums.PaymentStatus;
import com.mel.ctt.domain.enums.Payment_type;
import com.mel.ctt.dto.request.PaymentDtoOrderRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@Enumerated(EnumType.STRING)
	private Payment_type type;

	private String paymentProcessedAddress;

	public Payment() {

	}

	public Payment(PaymentDtoOrderRequest paymentDtoRequest) {
		this.setPaymentProcessedAddress(paymentDtoRequest.getPaymentProcessedAddress());
		this.setType(paymentDtoRequest.getType());

		if (paymentDtoRequest.getType() == Payment_type.CREDIT) {
			this.setStatus(PaymentStatus.WAITING);
		} else {
			Random random = new Random();
			int numero = random.nextInt(2);
			if (numero == 1) {
				this.setStatus(PaymentStatus.APPROVED);
			} else {
				this.setStatus(PaymentStatus.REPROVED);
			}

		}
	}

}
