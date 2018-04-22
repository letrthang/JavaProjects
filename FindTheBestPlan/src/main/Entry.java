package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.UserPlanController;
import model.Feature;
import model.Plan;
import model.User;
import view.PlanView;
import view.View;

/**
 * Entry of the program.
 * 
 * @author Thang Le.
 *
 */
public class Entry {

	public static void main(String[] args) {
		Feature F1 = new Feature("F1");
		Feature F2 = new Feature("F2");
		Feature F3 = new Feature("F3");

		List<Plan> allPlans = new ArrayList<>(); // this is the list of plans available instantiated as per the above
		Plan p1 = new Plan();
		p1.setName("P1");
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		Plan p2 = new Plan();
		p2.setName("P2");
		p2.setCost(15);
		List<Feature> p2Features = Arrays.asList(F1);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		Plan p3 = new Plan();
		p3.setName("P3");
		p3.setCost(20);
		List<Feature> p3Features = Arrays.asList( F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F1);
		user.setSelectedFeatures(userFeatures);

		// controller
		UserPlanController<View> userPlanController = new UserPlanController<View>();
		// view
		PlanView planView = new PlanView();
		userPlanController.addView(planView);
		// test
		userPlanController.findBestPlan(user, allPlans);
		userPlanController.viewBestPlans();
	}

}
