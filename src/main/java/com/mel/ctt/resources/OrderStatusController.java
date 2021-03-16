package com.mel.ctt.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mel.ctt.domain.OrderStatus;
import com.mel.ctt.dto.request.OrderStatusDtoRequest;
import com.mel.ctt.service.OrderStatusService;

@RestController
@RequestMapping("/orderstatus")
public class OrderStatusController {

	@Autowired
	private OrderStatusService orderStatusService;

	@GetMapping
	public List<OrderStatus> findAll() {
		return this.orderStatusService.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public OrderStatus findById(@PathVariable Long id) {
		OrderStatus orderStatus = orderStatusService.findById(id);
		return orderStatus;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody OrderStatusDtoRequest orderStatusDtoRequest) {
		this.orderStatusService.create(orderStatusDtoRequest);
	}

//	@PutMapping(path = "/{id}")
//	public void update(@RequestBody OrderStatusDtoRequest orderStatusDtoRequest, @PathVariable Long id) {
//		this.orderStatusService.update(orderStatusDtoRequest, id);
//	}

}
