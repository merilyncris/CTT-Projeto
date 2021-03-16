package com.mel.ctt.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mel.ctt.domain.Order;
import com.mel.ctt.dto.request.OrderDtoRequest;
import com.mel.ctt.service.OrderService;

@RequestMapping("/orders")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@PostMapping
	public void create(@Valid @RequestBody OrderDtoRequest orderDtoRequest) {
		orderService.create(orderDtoRequest);
	}

//	@PutMapping(path="/{id}")
//	public void update(@RequestBody OrderStatusDtoRequest orderDtoRequest, @PathVariable Long id) {
//		orderService.update(orderDtoRequest, id);
//	}	
}
