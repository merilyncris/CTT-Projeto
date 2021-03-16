package com.mel.ctt.dto.response;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mel.ctt.domain.Order;
import com.mel.ctt.domain.OrderStatus;
import com.mel.ctt.domain.Product;

import lombok.Data;

@Data
public class OrderDtoResponse {

	private OrderStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime localDateTime;

	private String code;

	private List<Product> products = new ArrayList<>();

	public OrderDtoResponse() {

	}

	public OrderDtoResponse(Order order) {
		this.setCode(order.getCode());
		this.setLocalDateTime(order.getLocalDateTime());
		this.setStatus(order.getStatus());
		this.setProducts(order.getProducts());
	}

}
