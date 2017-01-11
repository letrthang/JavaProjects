package math.util;

import java.net.MalformedURLException;
import java.net.URL;

import gen.math.util.CalcInterface;
import gen.math.util.CalcService;

public class TestClient {

	public static void main(String[] args) throws MalformedURLException {
		
		URL url = new URL("http://localhost:8080/CXFservicesMaven/services/soapservices?wsdl");
		CalcService ser = new CalcService(url);
		CalcInterface cal = ser.getCalcPort();
		
		System.out.println("add =" + cal.add(10, 100));
		System.out.println("mul =" + cal.multiply(5, 100));
	}

}
