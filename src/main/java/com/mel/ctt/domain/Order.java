package com.mel.ctt.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mel.ctt.dto.request.OrderDtoRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "status_id")
	private OrderStatus status;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	private String deliveredAddress;

	@Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime localDateTime;

	private String code;

	@ManyToMany
	@JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products = new ArrayList<>();

	public Order() {

	}

	public Order(OrderDtoRequest orderRequest) {
		this.fillFromDto(orderRequest);
		this.setLocalDateTime(LocalDateTime.now());
	}

	public void fillFromDto(OrderDtoRequest orderDtoRequest) {
		this.setDeliveredAddress(orderDtoRequest.getDeliveredAddress());
	}

}
