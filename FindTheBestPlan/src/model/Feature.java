package model;

public class Feature {
	private int id;
	private String Name;

	public Feature(String name) {
		this.Name = name;
	}

	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
