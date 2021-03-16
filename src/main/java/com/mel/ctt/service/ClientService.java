package com.mel.ctt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mel.ctt.domain.Client;
import com.mel.ctt.domain.Order;
import com.mel.ctt.dto.request.ClientDtoRequest;
import com.mel.ctt.dto.response.ClientDtoResponse;
import com.mel.ctt.dto.response.OrderDtoResponse;
import com.mel.ctt.exception.NotFound;
import com.mel.ctt.repository.ClientRepository;
import com.mel.ctt.repository.OrderRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private OrderRepository orderRepository;

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client findById(Long id) {
		Client client = this.clientRepository.findById(id).orElseThrow(() -> new NotFound());
		return client;
	}

	public ClientDtoResponse findByIdResponseDto(Long id) {
		Client client = this.clientRepository.findById(id).orElseThrow(() -> new NotFound());
		return new ClientDtoResponse(client);
	}

	public void create(ClientDtoRequest clientDtoRequest) {
		Client client = new Client(clientDtoRequest);
		clientRepository.save(client);
	}

	public Client update(ClientDtoRequest clientDtoRequest, Long id) {
		Client client = this.findById(id);
		client.fillFromDto(clientDtoRequest);

		return clientRepository.save(client);
	}

	public Optional<Client> findByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

	public List<OrderDtoResponse> findOrderByStatus(Long id) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new NotFound());
		List<Order> orders = orderRepository.findByClientOrderByStatus(client);
		List<OrderDtoResponse> orderOrderedByStatus = orders.stream().map(OrderDtoResponse::new)
				.collect(Collectors.toList());

		return orderOrderedByStatus;
	}

	public List<OrderDtoResponse> findClientOrders(Long id) {
		Client client = this.clientRepository.findById(id).orElseThrow(() -> new NotFound());
		List<OrderDtoResponse> orders = client.getOrders().stream().map(OrderDtoResponse::new)
				.collect(Collectors.toList());
		return orders;
	}

}
