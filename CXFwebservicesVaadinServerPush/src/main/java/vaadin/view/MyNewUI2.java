package vaadin.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Label;

import app.model.User;

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

		// build grid data

		// List<User> usrList = Arrays.asList(new User("mail", "thang", "123",
		// "SG"),
		// new User("mail2", "thang2", "1232", "SG2"));
		List<User> usrList = new ArrayList<>();
		usrList.add(new User("mail1", "thang1", "1231", "SG1"));
		usrList.add(new User("mail2", "thang2", "1232", "SG2"));

		if (grid.getColumns().isEmpty() == false) {
			grid.removeAllColumns();
			grid.addColumn(User::getEmail).setCaption("user-email");
			grid.addColumn(User::getName).setCaption("user-name");
			grid.addColumn(User::getPhone).setCaption("user-phone");
			grid.addColumn(User::getAddress).setCaption("user-address");
		}
		List<Column<User, ?>> cols = grid.getColumns();
		for (Column col : cols) {
			System.out.println("id: " + col.getId() + ". name :" + col.getCaption());
		}
		
		grid.setItems(usrList);

		grid.addItemClickListener(e -> {
			usrList.add(new User("mail3", "thang3", "1233", "SG3"));
			usrList.add(new User("mail4", "thang3", "1234", "SG4"));
			getUI().access(new Runnable() {
				@Override
				public void run() {
					grid.getDataProvider().refreshAll();
				}
			});

		});
		addComponent(grid);

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

	public String getText() {
		return textField.getValue();
	}

}
