package com.mel.ctt.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mel.ctt.domain.Client;

import lombok.Data;

@Data
public class ClientDtoResponse {

	private String email;

	private String address;

	private List<OrderDtoResponse> orders = new ArrayList<>();

	public ClientDtoResponse(Client client) {
		this.email = client.getEmail();
		this.address = client.getAddress();
		this.orders = client.getOrders().stream().map(OrderDtoResponse::new).collect(Collectors.toList());
	}

}
