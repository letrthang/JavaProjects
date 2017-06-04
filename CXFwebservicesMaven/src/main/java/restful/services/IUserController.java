package restful.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
/**
 *  "readUser" which is only accepted GET request. So url should be:
 *  http://localhost:8080/CXFwebservicesMaven-1.0.0/restservices/rest/user/12345
 *  
 *  "createUser" which is only accepted POST request. need use some tools as SoapUI
 *  to send POST request to server
 *  
 * */
@Path("/rest")
public interface IUserController {
	@POST
	@Path("/user")
	@Produces("application/json")
	@Consumes("application/json")
	public User createUser(User usr);
	
	@GET
	@Path("/user/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public User readUser(@PathParam("id") String id);
	
	
}
