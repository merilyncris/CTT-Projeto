package com.mel.ctt.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductDtoOrderRequest {

	private List<Long> ids = new ArrayList<>();

}
