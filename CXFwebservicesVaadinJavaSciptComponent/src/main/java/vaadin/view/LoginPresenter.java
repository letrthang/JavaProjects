package vaadin.view;

import com.vaadin.server.ClientConnector.DetachEvent;
import com.vaadin.server.ClientConnector.DetachListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LoginPresenter {

	private BaseView baseView;
	private UI ui;

	public interface BaseView {
		void setButtonLogin(String value);

		Button getButtonLogin();

		void setButtonCreateUser();

		Button getButtonCreateUser();

		void setLabelEmail(String value);

		String getLabelEmail();

		void setLabelPassowrd(String value);

		PasswordField getLabelPassowrd();

		void setLabelNoticeMessage(String value);

		void initLinkLecompany();

		void doNavigate(String viewName);
	}

	public LoginPresenter(BaseView view) {
		this.baseView = view;

	}

	public void setupUI(UI ui) {
		this.ui = ui;
		setAdditionalListeners();
	}

	private void setAdditionalListeners() {
		baseView.getButtonLogin().addClickListener(new ButtonLoginClickAction());
		baseView.getButtonCreateUser().addClickListener(new ButtonCreateUserClickAction());
		// init Link
		baseView.initLinkLecompany();
		// process when user refreshes browser causing UI detach.
		ui.addDetachListener(new UIDetachAction());
	}

	// ========== All Listeners here ==========

	private class ButtonLoginClickAction implements ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			{
				// all emailID is set to lower case.
				String inputEmail = baseView.getLabelEmail().toLowerCase().trim();
				String inputPassowrd = baseView.getLabelPassowrd().getValue();

				baseView.doNavigate(CreateUserForm.VIEW_NAME);

				// "uiSession" based on web browser. Above UIId based on each
				// Tab (request) of browser.
				String uiSession = ui.getSession().getSession().getId();
				System.out.println("uisession: " + uiSession + ". uiId: " + ui.getUIId());
			}
		}
	}

	private class ButtonCreateUserClickAction implements ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			baseView.doNavigate(CreateUserForm.VIEW_NAME);
		}

	}

	private class UIDetachAction implements DetachListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void detach(DetachEvent event) {
			System.out.println("UI detach at LoginPresenter");
		}

	}

}
