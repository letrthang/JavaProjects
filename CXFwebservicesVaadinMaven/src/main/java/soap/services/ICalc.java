package soap.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ICalc {

	@WebMethod
	int add(int a, int b);

	@WebMethod
	int multiply(int a, int b);

}