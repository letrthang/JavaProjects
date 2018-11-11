package com.lecompany.ui.views.storefront.events;

import com.vaadin.flow.component.ComponentEvent;
import com.lecompany.ui.views.orderedit.OrderEditor;

public class ReviewEvent extends ComponentEvent<OrderEditor> {

	public ReviewEvent(OrderEditor component) {
		super(component, false);
	}
}