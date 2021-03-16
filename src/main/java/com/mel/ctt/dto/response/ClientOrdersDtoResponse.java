package com.mel.ctt.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.mel.ctt.domain.Order;

import lombok.Data;

@Data
public class ClientOrdersDtoResponse {

	private List<Order> orders = new ArrayList<>();
}
