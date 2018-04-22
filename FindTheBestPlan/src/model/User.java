package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id; // unique id
	private String name;
	private List<Feature> selectedFeatures; // this is the list of features the user wants.

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the selectedFeatures
	 */
	public List<Feature> getSelectedFeatures() {
		return selectedFeatures;
	}

	/**
	 * @param selectedFeatures
	 *            the selectedFeatures to set
	 */
	public void setSelectedFeatures(List<Feature> selectedFeatures) {
		if (this.selectedFeatures == null) {
			this.selectedFeatures = new ArrayList<>();
		}
		this.selectedFeatures.addAll(selectedFeatures);
	}
}
