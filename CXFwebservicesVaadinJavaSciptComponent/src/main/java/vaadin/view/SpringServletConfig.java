package vaadin.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.spring.server.SpringVaadinServlet;

/**
 * All requests to /vaadin/ will be handled by Vaadin.
 * 
 * All requests to /webservices/ will be handled by CXF.
 * 
 * Example:
 * 
 * http://localhost:8080/$(projectname)/vaadin
 * http://localhost:8080/$(projectname)/webservices
 */
@WebServlet(urlPatterns = { "/vaadin/*", "/VAADIN/*" }, asyncSupported = true)
@VaadinServletConfiguration(productionMode = true, ui = MyUI.class)
public class SpringServletConfig extends SpringVaadinServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}