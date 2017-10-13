package com.hotmail.thang.ui.view.admin.product;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.hotmail.thang.backend.data.entity.Product;
import com.hotmail.thang.backend.service.ProductService;
import com.hotmail.thang.ui.navigation.NavigationManager;
import com.hotmail.thang.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class ProductAdminPresenter extends AbstractCrudPresenter<Product, ProductService, ProductAdminView> {

	@Autowired
	public ProductAdminPresenter(ProductAdminDataProvider productAdminDataProvider, NavigationManager navigationManager,
			ProductService service) {
		super(navigationManager, service, productAdminDataProvider);
	}
}
