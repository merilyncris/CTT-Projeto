package com.mel.ctt.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mel.ctt.domain.Client;
import com.mel.ctt.dto.request.ClientDtoRequest;
import com.mel.ctt.dto.response.ClientDtoResponse;
import com.mel.ctt.dto.response.OrderDtoResponse;
import com.mel.ctt.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public List<Client> findAll() {
		return clientService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody ClientDtoRequest clientDtoRequest) {
		clientService.create(clientDtoRequest);
	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Client update(@Valid @RequestBody ClientDtoRequest clientDtoRequest, @PathVariable Long id) {
		return this.clientService.update(clientDtoRequest, id);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ClientDtoResponse findByIdResponseDto(@PathVariable Long id) {
		return clientService.findByIdResponseDto(id);
	}

	@GetMapping("/orders")
	public List<OrderDtoResponse> findClientOrders(@RequestParam(value = "id") Long id,
			@RequestParam(value = "orderedStatus", defaultValue = "false") Boolean order) {
		return order == false ? clientService.findClientOrders(id) : clientService.findOrderByStatus(id);
	}

//	@GetMapping("/ordersStatus")
//	public List<OrderDtoResponse> findOrderByStatus(@RequestParam(value="id") Long id){
//		return clientService.findOrderByStatus(id);
//	}

//	@GetMapping
//	public Client findByEmail(@RequestParam String email) {
//		return this.clientService.findByEmail(email);
//	}
}
