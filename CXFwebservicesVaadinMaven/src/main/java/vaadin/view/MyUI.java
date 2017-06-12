package vaadin.view;

import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Component
@Theme("runo")
@SpringUI
public class MyUI extends UI {

	/**
	 * Theme "runo" is get from /VAADIN/ directory
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();

		final TextField name = new TextField();
		name.setCaption("Type your name here:");

		final ComboBox<String> combo = new ComboBox<String>("combobox");
		combo.setItems("one", "two", "three");
		combo.addSelectionListener(e -> {
			layout.addComponent(new Label("combobox " + combo.getSelectedItem().toString() + ", is selected"));
		});

		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
		});

		layout.addComponents(name, button, combo);

		setContent(layout);

	}

}