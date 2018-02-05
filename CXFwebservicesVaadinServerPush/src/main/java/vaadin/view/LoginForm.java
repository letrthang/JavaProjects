package vaadin.view;

import vaadin.view.LoginPresenter.BaseView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;

/**
 * @author Thang Le
 *
 *         Using "UIScope" annotation to avoid spring creating singleton class
 *         for this view
 */

@SpringComponent
@UIScope
public class LoginForm extends LoginView implements BaseView, View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "LoginForm";

	@Override
	public void enter(ViewChangeEvent event) {
		passwordField.setValue("");
		lbNoticeMessage.setValue("");
	}

	@Override
	public void setButtonLogin(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Button getButtonLogin() {
		// TODO Auto-generated method stub
		return button;
	}

	@Override
	public void setButtonCreateUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public Button getButtonCreateUser() {
		// TODO Auto-generated method stub
		return button1;
	}

	@Override
	public void setLabelEmail(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLabelEmail() {
		// TODO Auto-generated method stub
		return textField.getValue();
	}

	@Override
	public void setLabelPassowrd(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public PasswordField getLabelPassowrd() {
		// TODO Auto-generated method stub
		return passwordField;
	}

	@Override
	public void setLabelNoticeMessage(String value) {
		lbNoticeMessage.setValue(value);
	}

	@Override
	public void initLinkLecompany() {
		// Hyperlink to a given URL
		linkLecompany.setResource(new ExternalResource("https://lecompany.co/"));
		// Open the URL in a new window/tab
		linkLecompany.setTargetName("_blank");

	}

	@Override
	public void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);

	}

}
