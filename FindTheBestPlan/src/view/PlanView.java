package view;

import java.util.ArrayList;
import java.util.List;

import model.Plan;

public class PlanView extends View {

	List<Plan> plans;

	@Override
	public void viewResult() {
		String bestPlans = "";
		if (plans != null && plans.size() > 0) {
			for (Plan p : plans) {
				bestPlans += p.getName();
			}
			System.out.println(bestPlans);
		} else {
			System.out.println("No plan found!");
		}
	}

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
		if (plans != null) {
			this.plans.addAll(plans);
		}
	}

}
