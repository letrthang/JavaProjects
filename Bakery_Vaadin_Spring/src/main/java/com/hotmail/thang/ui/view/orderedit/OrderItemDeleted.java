package com.hotmail.thang.ui.view.orderedit;

import com.hotmail.thang.backend.data.entity.OrderItem;

public class OrderItemDeleted {

	private OrderItem orderItem;

	public OrderItemDeleted(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}
}
