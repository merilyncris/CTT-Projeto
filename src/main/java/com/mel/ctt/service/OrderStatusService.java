package com.mel.ctt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mel.ctt.domain.OrderStatus;
import com.mel.ctt.dto.request.OrderStatusDtoRequest;
import com.mel.ctt.repository.OrderStatusRepository;

@Service
public class OrderStatusService {

	@Autowired
	private OrderStatusRepository orderStatusRepository;

	public List<OrderStatus> findAll() {
		List<OrderStatus> orderStatus = orderStatusRepository.findAll();
		return orderStatus;
	}

	public OrderStatus findById(Long id) {
		Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
		return orderStatus.orElse(null);
	}

	public void create(OrderStatusDtoRequest orderStatusDtoRequest) {
		OrderStatus orderStatus = new OrderStatus(orderStatusDtoRequest);
		orderStatusRepository.save(orderStatus);

		// setting code
		Long gettingId = orderStatus.getId();
		String processingCode = Long.toString(gettingId);
		String outsiderCode = orderStatus.getCode();

		String finalCode;

		if (gettingId < 10) {
			finalCode = outsiderCode + "0" + processingCode;
		} else {
			finalCode = outsiderCode + processingCode;
		}

		orderStatus.setCode(finalCode);
		orderStatusRepository.save(orderStatus);
		// setting code end

		String finalName = orderStatus.getName() + " Status Code " + finalCode;

		orderStatus.setName(finalName);

		orderStatusRepository.save(orderStatus);

	}

//	public void update(OrderStatusDtoRequest orderStatusDtoRequest, Long id) {
//		OrderStatus orderStatus = this.findById(id);
//		orderStatus.setName(orderStatusDtoRequest.getName());
//		orderStatus.setPaymentStatus(orderStatusDtoRequest.getPaymentStatus());
//		orderStatusRepository.save(orderStatus);
//
//		// setting code
//		Long gettingId = orderStatus.getId();
//		String processingCode = Long.toString(gettingId);
//		String outsiderCode = orderStatus.getCode();
//
//		String finalCode;
//
//		if (gettingId < 10) {
//			finalCode = outsiderCode + "0" + processingCode;
//		} else {
//			finalCode = outsiderCode + processingCode;
//		}
//
//		orderStatus.setCode(finalCode);
//		orderStatusRepository.save(orderStatus);
//		// setting code end
//
//		String finalName = orderStatus.getName() + " Status Code " + finalCode;
//
//		orderStatus.setName(finalName);
//
//		orderStatusRepository.save(orderStatus);
//	}
}
