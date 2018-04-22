package model;

import java.util.ArrayList;
import java.util.List;

public class Plan {
	private int id;
	private String Name;
	private double Cost;
	private List<Feature> Features;

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
	 * @return the cost
	 */
	public double getCost() {
		return Cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(double cost) {
		Cost = cost;
	}

	/**
	 * @return the features
	 */
	public List<Feature> getFeatures() {
		return Features;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(List<Feature> features) {
		if (this.Features == null) {
			this.Features = new ArrayList<>();
		}
		
		if (features != null) {
			this.Features.addAll(features);
		}
	}

}
