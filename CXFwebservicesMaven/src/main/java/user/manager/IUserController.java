package user.manager;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
