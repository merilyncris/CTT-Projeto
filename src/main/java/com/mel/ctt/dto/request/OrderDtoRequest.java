package com.mel.ctt.dto.request;

import lombok.Data;

@Data
public class OrderDtoRequest {

	private String email;

	private PaymentDtoOrderRequest payment;

	private String deliveredAddress;

	private ProductDtoOrderRequest products;

}
