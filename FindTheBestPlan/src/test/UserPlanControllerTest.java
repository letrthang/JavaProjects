package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.UserPlanController;
import model.Feature;
import model.Plan;
import model.User;
import view.PlanView;
import view.View;

public class UserPlanControllerTest {

	List<Plan> allPlans = new ArrayList<>(); // this is the list of plans available instantiated as

	// Features
	Feature F1 = new Feature("F1");
	Feature F2 = new Feature("F2");
	Feature F3 = new Feature("F3");
	// Plans
	Plan p1 = new Plan("P1");
	Plan p2 = new Plan("P2");
	Plan p3 = new Plan("P3");

	// view
	PlanView planView = new PlanView();
	// controller
	UserPlanController<View> userPlanController = new UserPlanController<View>(planView);

	@Before
	public void beforeEachTest() {
		allPlans.clear();
	}

	@Test
	public void test1() {
		// Plans
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		p2.setCost(15);
		List<Feature> p2Features = Arrays.asList(F2);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		p3.setCost(20);
		List<Feature> p3Features = Arrays.asList(F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F1);
		user.setSelectedFeatures(userFeatures);

		// test
		List<Plan> plans = userPlanController.findBestPlan(user, allPlans);

		Assert.assertArrayEquals(Arrays.asList(p1).toArray(), plans.toArray());
	}

	@Test
	public void test2() {
		// Plans
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1, F2);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		p2.setCost(15);
		List<Feature> p2Features = Arrays.asList(F2, F3);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		p3.setCost(20);
		List<Feature> p3Features = Arrays.asList(F1, F2, F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F2, F3);
		user.setSelectedFeatures(userFeatures);

		// test
		List<Plan> plans = userPlanController.findBestPlan(user, allPlans);

		Assert.assertArrayEquals(Arrays.asList(p2).toArray(), plans.toArray());
	}

	@Test
	public void test3() {
		// Plans
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1, F2);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		p2.setCost(15);
		List<Feature> p2Features = Arrays.asList(F2, F3);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		p3.setCost(20);
		List<Feature> p3Features = Arrays.asList(F1, F2, F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F1, F3);
		user.setSelectedFeatures(userFeatures);

		// test
		List<Plan> plans = userPlanController.findBestPlan(user, allPlans);

		Assert.assertArrayEquals(Arrays.asList(p3).toArray(), plans.toArray());
	}

	@Test
	public void test4() {
		// Plans
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1, F2);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		p2.setCost(14);
		List<Feature> p2Features = Arrays.asList(F2, F3);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		p3.setCost(25);
		List<Feature> p3Features = Arrays.asList(F1, F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F1, F3);
		user.setSelectedFeatures(userFeatures);

		// test
		List<Plan> plans = userPlanController.findBestPlan(user, allPlans);

		Assert.assertArrayEquals(Arrays.asList(p1, p2).toArray(), plans.toArray());
	}

	@Test
	public void test5() {
		// Plans
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1, F2);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		p2.setCost(15);
		List<Feature> p2Features = Arrays.asList(F2, F3);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		p3.setCost(25);
		List<Feature> p3Features = Arrays.asList(F1, F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F1, F3);
		user.setSelectedFeatures(userFeatures);

		// test
		List<Plan> plans = userPlanController.findBestPlan(user, allPlans);

		Assert.assertArrayEquals(Arrays.asList(p3).toArray(), plans.toArray());
	}

	@Test
	public void test6() {
		// Plans
		p1.setCost(10);
		List<Feature> p1Features = Arrays.asList(F1);
		p1.setFeatures(p1Features);
		allPlans.add(p1);

		p2.setCost(15);
		List<Feature> p2Features = Arrays.asList(F2);
		p2.setFeatures(p2Features);
		allPlans.add(p2);

		p3.setCost(25);
		List<Feature> p3Features = Arrays.asList(F3);
		p3.setFeatures(p3Features);
		allPlans.add(p3);

		// User
		User user = new User();
		List<Feature> userFeatures = Arrays.asList(F1, F2, F3);
		user.setSelectedFeatures(userFeatures);

		// test
		List<Plan> plans = userPlanController.findBestPlan(user, allPlans);

		Assert.assertArrayEquals(Arrays.asList(p1, p2, p3).toArray(), plans.toArray());
	}
}
