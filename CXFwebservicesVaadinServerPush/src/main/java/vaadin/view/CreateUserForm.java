package vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import vaadin.view.CreateUserPresenter.BaseView;

@SpringComponent
@UIScope
public class CreateUserForm extends CreateUserView implements BaseView, View {
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "CreateUserForm";

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setButtonTermsConditions(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Button getButtonTermsConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTextfieldEmail(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public TextField getTextFieldEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordField getUserPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PasswordField getUserPasswordRetype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);
	}

}
