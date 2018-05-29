package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Animal;

@Path("/AnimalQuery")
public interface AnimalQuery {
	/**
	 * define the "type" by your requirement, it may be size, color, flyable, ...
	 * service endpoint looks like:
	 * 
	 * http://localhost/yourapp:8080/AnimalQuery/getAnimalByType
	 * 
	 * @param type
	 * @return
	 */
	@POST
	@Path("/getAnimalByType")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Animal> getAnimalByType(@HeaderParam("type") String type);
}
