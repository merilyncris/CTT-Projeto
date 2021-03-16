package com.mel.ctt.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mel.ctt.domain.Client;
import com.mel.ctt.domain.Order;
import com.mel.ctt.domain.OrderStatus;
import com.mel.ctt.domain.Payment;
import com.mel.ctt.domain.Product;
import com.mel.ctt.dto.request.OrderDtoRequest;
import com.mel.ctt.repository.ClientRepository;
import com.mel.ctt.repository.OrderRepository;
import com.mel.ctt.repository.OrderStatusRepository;
import com.mel.ctt.repository.PaymentRepository;
import com.mel.ctt.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderStatusRepository orderStatusRepository;

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public void create(OrderDtoRequest orderDtoRequest) {
		Order order = new Order(orderDtoRequest);
		Client client = clientRepository.findByEmail(orderDtoRequest.getEmail()).orElseGet(() -> {
			Client clientSave = new Client(orderDtoRequest.getEmail(), orderDtoRequest.getDeliveredAddress());
			return clientRepository.save(clientSave);

		});

		order.setPayment(paymentRepository.saveAndFlush(new Payment(orderDtoRequest.getPayment())));

		order.setProducts(productRepository.findProductByIdIn(orderDtoRequest.getProducts().getIds()));

		order.setClient(client);

		OrderStatus orderStatus = orderStatusRepository.findByPaymentStatus(order.getPayment().getStatus())
				.orElseThrow();
		orderStatusRepository.saveAndFlush(orderStatus);
		order.setStatus(orderStatus);

		// setting code
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy HH:mm");
		LocalDateTime dateTime = order.getLocalDateTime();
		String formattedDateTime = dateTime.format(formatter);
		String other = formattedDateTime.replaceAll("[^a-zA-Z0-9]", "");

		orderRepository.saveAndFlush(order);

		Long id = order.getId();
		String processingCode = Long.toString(id);
		String theFinalCode = other + processingCode;
		order.setCode(theFinalCode);
		// setting code

		List<Long> longs = orderDtoRequest.getProducts().getIds();
		List<Product> products = productRepository.findAllById(longs);

		order.setProducts(products);

		orderRepository.saveAndFlush(order);
	}

	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.orElse(null);
	}

	public List<Order> findByOrderByStatusAsc() {
		return this.findByOrderByStatusAsc();
	}

//	public void update(OrderStatusDtoRequest orderSatusDtoRequest, Long id) {
//		Order order = orderRepository.findById(id).orElseThrow(() -> new NotFound());
//		OrderStatus orderStatus = order.getStatus();
//		orderStatus.setPaymentStatus(orderSatusDtoRequest.getPaymentStatus());
//		orderStatus.setName(orderSatusDtoRequest.getName());
//		orderStatus.setCode(orderSatusDtoRequest.getCode());
//
//		orderStatusRepository.saveAndFlush(orderStatus);
//
//		Long idOrderStatus = orderStatus.getId();
//		String processingCode = Long.toString(idOrderStatus);
//		String outsiderCode = orderStatus.getCode();
//
//		String finalCode;
//
//		if (idOrderStatus < 10) {
//			finalCode = outsiderCode + "0" + processingCode;
//		} else {
//			finalCode = outsiderCode + processingCode;
//		}
//
//		orderStatus.setCode(finalCode);
//		order.setStatus(orderStatus);
//		orderRepository.save(order);
//	}
}
