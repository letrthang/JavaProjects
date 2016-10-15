package sample;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		String rer_map = "";
		
		if (arg0 == null)
			return;
		rer_map = arg0.getServletRequest().getParameter("Login");
		if (rer_map !=null && rer_map.equals("Login")) {
			// every time have a request from Login button, we increase number
			MySessionListener.onlineNumber++;
		}

	}

}
