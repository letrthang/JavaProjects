/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Thang Le
 *
 */
public class CombinatoryPlans {
	private double totalCost;
	private List<Plan> plans;
	private Set<Feature> features;

	/**
	 * @return the plans
	 */
	public List<Plan> getPlans() {
		return plans;
	}

	/**
	 * @param plans
	 *            the plans to set
	 */
	public void setPlans(List<Plan> plans) {
		if (this.plans == null) {
			this.plans = new ArrayList<>();
		}
		
		this.plans.addAll(plans);
	}

	public void populateFeaturesAndCost() {
		if (features == null) {
			features = new HashSet<>();
		}
		
		for (Plan p : plans) {
			features.addAll(p.getFeatures());
			totalCost = totalCost + p.getCost();
		}
	}

	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the features
	 */
	public Set<Feature> getFeatures() {
		return features;
	}

}
