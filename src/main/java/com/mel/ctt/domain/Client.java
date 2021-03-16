package com.mel.ctt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mel.ctt.dto.request.ClientDtoRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String address;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@ElementCollection
	private List<Order> orders = new ArrayList<>();

	public Client() {

	}

	public Client(ClientDtoRequest clientDtoRequest) {
		this.fillFromDto(clientDtoRequest);
	}

	public void fillFromDto(ClientDtoRequest clientDtoRequest) {
		this.setAddress(clientDtoRequest.getAddress());
		this.setEmail(clientDtoRequest.getEmail());
	}

	public Client(String email, String address) {
		this.setAddress(address);
		this.setEmail(email);
	}

}
