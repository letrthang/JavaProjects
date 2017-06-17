package vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;

@SpringComponent
@UIScope
public class MyNewUI extends NewUI implements View{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "MyNewUI";

	public MyNewUI() {
		button.addClickListener(e -> {
			addComponent(new Label("Thanks " + label.getValue() + ", it works!"));
		});

		comboBox.setItems("1", "2", "3");
		comboBox.addSelectionListener(e -> {
			boolean cb = checkBox.getValue();
			if (cb == true) {
				slider.setValue((double) 50);
			} else {
				doNavigate(MyNewUI2.VIEW_NAME);
			}
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	private void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);
	}
	public Label getLabel(){
		return label;
	}

}
