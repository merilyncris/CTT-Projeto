package com.mel.ctt.dto.request;

import com.mel.ctt.domain.enums.Payment_type;

import lombok.Data;

@Data
public class PaymentDtoOrderRequest {

	private Payment_type type;

	private String paymentProcessedAddress;

}
