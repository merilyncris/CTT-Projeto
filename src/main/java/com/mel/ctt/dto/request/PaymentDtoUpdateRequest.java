package com.mel.ctt.dto.request;

import com.mel.ctt.domain.enums.PaymentStatus;
import com.mel.ctt.domain.enums.Payment_type;

import lombok.Data;

@Data
public class PaymentDtoUpdateRequest {

	private Payment_type type;

	private String paymentProcessedAddress;

	private PaymentStatus status;

	public PaymentDtoUpdateRequest() {

	}

}
