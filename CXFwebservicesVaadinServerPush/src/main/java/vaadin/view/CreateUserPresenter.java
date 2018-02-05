package vaadin.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class CreateUserPresenter {

	private BaseView baseView;

	private UI ui;

	public interface BaseView {

		void setButtonTermsConditions(String value);

		Button getButtonTermsConditions();

		void setTextfieldEmail(String text);

		TextField getTextFieldEmail();

		PasswordField getUserPassword();

		PasswordField getUserPasswordRetype();

		void doNavigate(String viewName);
	}

	public CreateUserPresenter(BaseView view) {
		this.baseView = view;

	}
	
	public void setupUI(UI ui) {
		this.ui = ui;
	}

}
