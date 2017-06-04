package math.util;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://util.math/", portName = "CalcPort", serviceName = "CalcService")
public class Calc implements CalcInterface {	
	
	@WebMethod(operationName = "add", action = "urn:Add")
	public int add( @WebParam(name = "arg0") int a,  @WebParam(name = "arg1") int b) {
		return a + b;
	}

	@WebMethod(operationName = "multiply", action = "urn:Multiply")
	public int multiply(  @WebParam(name = "arg0") int a,  @WebParam(name = "arg1") int b) {
		return a * b;
	}
}