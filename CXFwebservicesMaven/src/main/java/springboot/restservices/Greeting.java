package springboot.restservices;

public class Greeting {
	private long id;
	private String name;

	Greeting(long Id, String Name) {
		id = Id;
		name = Name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
