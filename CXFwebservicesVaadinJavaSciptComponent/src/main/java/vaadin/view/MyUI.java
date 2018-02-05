package vaadin.view;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@Component
@Theme("valo")
@Widgetset("AppWidgetset")
@SpringUI
@Push
public class MyUI extends UI {

	/**
	 * Theme "runo" is get from /VAADIN/ directory
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CreateUserForm createUserForm;
	@Autowired
	LoginForm loginForm;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		// setContent(createUserForm);
		UI currentUI = UI.getCurrent();
		Navigator navigator = new Navigator(UI.getCurrent(), this);
		new CreateUserPresenter(createUserForm).setupUI(currentUI);
		new LoginPresenter(loginForm).setupUI(currentUI);

		navigator.addView(CreateUserForm.VIEW_NAME, createUserForm);
		navigator.addView(LoginForm.VIEW_NAME, loginForm);
		navigator.addView("", loginForm);

	}

}