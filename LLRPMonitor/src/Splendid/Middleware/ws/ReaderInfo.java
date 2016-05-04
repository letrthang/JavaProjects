package Splendid.Middleware.ws;

import javax.xml.ws.Endpoint;

public class ReaderInfo {
	private static Endpoint  endpoint = null;
	
	public static boolean readerServices(String IP){
		//build ws by wsgen tool:
		//wsgen -verbose -keep -cp . Splendid.Middleware.ws.ReaderInfoServices
		if(IP.equals("")){
			IP = "localhost:8080";
		}
		
		String endPointURL = "http://" + IP + "/stockID_Plugin/ReaderInfo";
		if(endpoint != null && endpoint.isPublished()){
			endpoint.stop();
		}
		
		try {
			endpoint = Endpoint.publish(endPointURL,new ReaderInfoServices());
		} catch (Exception e) {
			return false;
		}
		return true;			
	}
}
