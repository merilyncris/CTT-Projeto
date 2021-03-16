package com.mel.ctt.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mel.ctt.domain.enums.PaymentStatus;

import lombok.Data;

@Data
public class OrderStatusDtoRequest {

	@NotNull
	@Size(min = 5)
	private String name;

	@NotNull
	@Size(min = 3, max = 3)
	private String code;

	@NotNull
	private PaymentStatus paymentStatus;

}
