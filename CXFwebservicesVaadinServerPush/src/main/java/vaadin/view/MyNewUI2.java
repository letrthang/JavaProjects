package vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;

@SpringComponent
@UIScope
public class MyNewUI2 extends NewUI2 implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "MyNewUI2";

	public MyNewUI2() {

		button.addClickListener(e -> {
			doNavigate(MyNewUI3.VIEW_NAME);
		});

		button1.addClickListener(e -> {
			doNavigate(MyNewUI.VIEW_NAME);
		});

		label.setEnabled(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	private void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);
	}

	public Label getLabel() {
		return label;
	}

	public String getText(){
		return textField.getValue();
	}

}
