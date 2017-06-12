package vaadin.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.spring.server.SpringVaadinServlet;

//all requests to /vaadin/ will be handled by Vaadin
//all requests to /webservices/ will be handled by CXF
@WebServlet(urlPatterns = {"/vaadin/*"}, asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
public class MyUIServlet extends SpringVaadinServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}