package soap.services;

/**
 * @author Thang Le. 
 * www.letrungthang.blogspot.com
 * 
 * */

import javax.jws.WebService;

/**
 * The URL for this SOAP server should be:
 * http://localhost:8080/CXFwebservicesMaven-1.0.0/soapservices?wsdl
 * 
 */
@WebService(endpointInterface = "soap.services.ICalc", targetNamespace = "http://soap.services.test/", portName = "CalcPort", serviceName = "iCalc")
public class CalcImpl implements ICalc {
	public int add(int a, int b) {
		System.out.println("Server call service add. arg0 = " + a + "  arg1 = " + b);
		return a + b;
	}

	public int multiply(int a, int b) {
		System.out.println("Server call service multiply. arg0 = " + a + "  arg1 = " + b);
		return a * b;
	}
}