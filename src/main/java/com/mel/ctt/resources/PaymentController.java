package com.mel.ctt.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mel.ctt.domain.Payment;
import com.mel.ctt.domain.enums.PaymentStatus;
import com.mel.ctt.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping(path = "/{id}", produces = "application/json")
	public Payment findById(@PathVariable Long id) {
		return paymentService.findById(id);
	}

	@PutMapping(path = "/{id}", produces = "application/json")
	public void updateStatus(@RequestBody PaymentStatus status, @PathVariable Long id) {
		paymentService.updateStatus(status, id);
	}

}
