package restful.services;

/*
 * There is no need any annotation here, CXF will know and do a mapping to json/xml for you
 * 
 */
public class User {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
