/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import model.CombinatoryPlans;
import model.Feature;
import model.Plan;
import model.User;
import view.PlanView;
import view.View;

/**
 * This controller is to find best plan for user.
 * 
 * @author Thang Le.
 *
 */
public class UserPlanController<V extends View> {
	V view;

	public UserPlanController(V view) {
		this.view = view;
	}

	/**
	 * Get View
	 * 
	 * @param view
	 * @return
	 */
	public V getView(V view) {
		return this.view;
	}

	/**
	 * Find best cost plan: 1. we exclude all Plans not having any user requested
	 * feature. 2. start build combinatory of plans that have all user requested
	 * features. 3. do comparision all possible combinatories of plans above to get
	 * best cost.
	 * 
	 * @param user
	 * @param plan
	 * @return
	 */
	public List<Plan> findBestPlan(User user, List<Plan> avaiPlans) {
		List<Plan> bestPlans = null;

		// 1. we exclude all Plans not having any user requested feature.
		List<Plan> matchedPlans = new ArrayList<Plan>();
		for (Plan p : avaiPlans) {
			for (Feature f : user.getSelectedFeatures()) {
				if (p.getFeatures().contains(f)) {
					matchedPlans.add(p);
					break;
				}
			}
		}
		// 2. do calculate to find best plans using combinatoricslib library
		// https://github.com/dpaukov/combinatoricslib
		List<CombinatoryPlans> combinatoryPlansLst = new ArrayList<>();
		List<CombinatoryPlans> matchedUserCombinatoryPlansLst = new ArrayList<>();
		List<CombinatoryPlans> bestCodeAllLevelCombinatory = new ArrayList<>();

		ICombinatoricsVector<Plan> initialVector = null;

		for (int i = 0; i < matchedPlans.size(); i++) {
			initialVector = Factory.createVector(matchedPlans);
			// 1. form combinatorical of Plans, start from 1 plan
			Generator<Plan> gen = Factory.createSimpleCombinationGenerator(initialVector, i + 1);
			CombinatoryPlans combinatoryPlans = null;
			for (ICombinatoricsVector<Plan> combination : gen) {
				combinatoryPlans = new CombinatoryPlans();
				combinatoryPlans.setPlans(combination.getVector());
				combinatoryPlans.populateFeaturesAndCost();
				combinatoryPlansLst.add(combinatoryPlans);
			}
			// 2. check whether user features all matches with each combinatorical
			for (CombinatoryPlans combiPlans : combinatoryPlansLst) {
				if (combiPlans.getFeatures().containsAll(user.getSelectedFeatures())) {
					matchedUserCombinatoryPlansLst.add(combiPlans);
				}
			}

			// 3. get best cost for each Combinatory level. For example, best cost from 2
			// plans Combinatory, best code from 3 plans Combinatory...
			matchedUserCombinatoryPlansLst.sort((cp1, cp2) -> {
				return (int) (cp1.getTotalCost() - cp2.getTotalCost());
			});

			// 4. if found best cost
			if (matchedUserCombinatoryPlansLst.size() > 0) {
				CombinatoryPlans bestCodeLevelCombinatory = matchedUserCombinatoryPlansLst.get(0);
				bestCodeAllLevelCombinatory.add(bestCodeLevelCombinatory);
			} else {
				// 5. not found, continue with Combinatory of more plans
				combinatoryPlansLst.clear();
				matchedUserCombinatoryPlansLst.clear();
			}
		}
		// 6. sort all level Combinatory list
		bestCodeAllLevelCombinatory.sort((cp1, cp2) -> {
			return (int) (cp1.getTotalCost() - cp2.getTotalCost());
		});

		// 7. take the lowest cost combinatory.
		if (bestCodeAllLevelCombinatory.size() > 0) {
			bestPlans = bestCodeAllLevelCombinatory.get(0).getPlans();
		}

		if (bestPlans != null) {
			// 8. update result on the View
			viewBestPlans(bestPlans);
		}

		return bestPlans;
	}

	/**
	 * View best plans
	 * 
	 * @param bestPlans
	 */
	private void viewBestPlans(List<Plan> bestPlans) {
		((PlanView) this.view).setPlans(bestPlans);
		view.viewResult();
	}
}
