package sample;

import javax.servlet.http.*;

public class MySessionListener implements HttpSessionListener {
	public static int onlineNumber = 0;

	public MySessionListener() {
	}

	public void sessionCreated(HttpSessionEvent sessionEvent) {
		// Get the session
		// HttpSession session = sessionEvent.getSession();
		onlineNumber++;
		try {
			// System.out.println("Session created: "+ session);
			// session.setAttribute("foo","bar");
		} catch (Exception e) {
			System.out.println("Error in setting session attribute: " + e.getMessage());
		}
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		// Get the session that was invalidated
		// HttpSession session = sessionEvent.getSession();
		// Log a message
		// System.out.println("Session invalidated: "+session);
		// System.out.println("The name is: " + session.getAttribute("foo"));
		onlineNumber--;
	}
}
