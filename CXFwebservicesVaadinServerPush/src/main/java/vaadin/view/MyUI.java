package vaadin.view;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@Component
@Theme("runo")
@SpringUI
@Push
public class MyUI extends UI {

	/**
	 * Theme "runo" is get from /VAADIN/ directory
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MyNewUI3 myNewUI3;
	@Autowired
	MyNewUI2 myNewUI2;
	@Autowired
	MyNewUI myNewUI;

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		setContent(myNewUI);
		setContent(myNewUI2);
		setContent(myNewUI3);

		Navigator navigator = new Navigator(UI.getCurrent(), this);
		navigator.addView(MyNewUI.VIEW_NAME, myNewUI);
		navigator.addView(MyNewUI2.VIEW_NAME, myNewUI2);
		navigator.addView(MyNewUI3.VIEW_NAME, myNewUI3);
		navigator.addView("", myNewUI);

		// Start the data feed thread
		new FeederThread().start();
	}

	class FeederThread extends Thread {

		int count = 0;

		@Override
		public void run() {
			try {
				// Update the data for a while
				while (count < 10000) {
					Thread.sleep(1000);

					// Calling special 'access' method on UI object, for
					// inter-thread communication.
					access(new Runnable() {
						@Override
						public void run() {
							count++;
							tellTime();
						}
					});
					access(new Runnable() {
						@Override
						public void run() {
							tellTime2();
						}
					});
					access(new Runnable() {
						@Override
						public void run() {
							tellTime3();
						}
					});
				}

				// Inform that we have stopped running
				// Calling special 'access' method on UI object, for
				// inter-thread communication.
				access(new Runnable() {
					@Override
					public void run() {
						myNewUI.getLabel().setValue("Too tired to count. sleep :))");
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void tellTime() {
		//System.out.println("tellTime");
		myNewUI.getLabel().setValue("Now : " + Instant.now());
	}

	public void tellTime2() {
		//System.out.println("tellTime2");
		myNewUI2.getLabel().setValue("Now : " + Instant.now());
		//System.out.println("get text NewUI2: " + myNewUI2.getText());
	}

	public void tellTime3() {
		//System.out.println("tellTime3");
		myNewUI3.getLabel().setValue("Now : " + Instant.now());
	}

}