package thangle.trains.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import thangle.trains.algorithm.Algorithms;
import thangle.trains.model.Graph;
import thangle.trains.model.Node;
import thangle.trains.view.GraphUserView;

public class GraphController {

	// To save all routes were found in method "FindAllRoute"
	public static Set<List<Integer>> foundRoutes;
	Graph graph; // store a graph
	Algorithms algor; // for graph calculation
	GraphUserView graphUserView; // for view

	/**
	 * constructor
	 */
	public GraphController(Graph g) {
		this.graph = g;
		// init algor
		algor = new Algorithms(graph);
		// init graphUserView
		graphUserView = new GraphUserView();
		// init foundRoutes
		foundRoutes = new HashSet<List<Integer>>();
	}

	/**
	 * calculate the distance of a given path.
	 * Distance of path ABCD = AB + BC + CD. One of them is zero, then distance is zero.
	 * 
	 * @param route
	 *            given route
	 * @return distance of route or zero if the given path cannot route
	 */
	public Integer CalculateRouteDistance(List<Integer> route) {
		Integer distance = 0;
		Integer distance2AdjNodes = null;

		if (route.size() >= 2) {
			for (Integer i = 0; i < route.size() - 1; i++) {
				distance2AdjNodes = graph.Nodes.get(route.get(i)).GetWei().get(route.get(i + 1));
				if (distance2AdjNodes == null) {
					distance = 0; // no link between two adjacent nodes in the given path
					break; // so, quit calculation
				}
				// Nightmare code because i wrote it at midnight :))
				distance += graph.Nodes.get(route.get(i)).GetWei().get(route.get(i + 1));

			}
		}

		return distance;
	}

	/**
	 * we generate a set of combinatorial routes with condition of
	 * max number of input given nodes. It uses the combinatorial algorithm for
	 * calculation. Please refer: https://code.google.com/p/combinatoricslib/
	 * 
	 * @param inputRoutes
	 *            (input): input routes. we combine these input routes to get final merged route
	 *            that satisfy the input condition (maxNodes).
	 * @param outputRoutes
	 *            (output): store the result
	 * @param numberElement
	 *            (input): number of routes of each combinatory
	 * @param maxNodes
	 *            (input): max nodes of each given route
	 */
	public void CombinatoryRoutesWithNumberNodes(Set<List<Integer>> inputRoutes, Set<List<Integer>> outputRoutes,
			Integer numberElement, Integer maxNodes) {

		// we add all routes to a map that key is route's ID (= 0,1,2...), value is the route
		HashMap<Integer, List<Integer>> allFoundRoute = new HashMap<Integer, List<Integer>>();
		List<Integer> routeID = new ArrayList<Integer>();
		Integer n = 0;
		for (List<Integer> i : inputRoutes) {
			allFoundRoute.put(n, i);
			routeID.add(n);
			n++;
		}
		// we generate set of combinatorial of routes from input "inputRoutes"
		ICombinatoricsVector<Integer> originalVector = Factory.createVector(routeID);
		Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(originalVector, numberElement);
		for (ICombinatoricsVector<Integer> perm : gen) {
			// we get each permutation (perm) and calculate total number of nodes/stops.
			// Eg. if Permutation = [0,1,2], then total nodes = number of nodes of (route0 + route1 + route2)
			Integer totalNodes = 0;
			for (Integer j : perm) {
				// number of stop of a route = size of list -1.
				totalNodes += (allFoundRoute.get(j).size() - 1);
			}
			// if totalNodes < maxNodes, we merge these routes in permutation to a final route
			if (totalNodes < maxNodes) {
				List<Integer> mergeList = new ArrayList<Integer>();
				for (Integer j : perm) {
					mergeList.addAll(allFoundRoute.get(j));
				}

				// remove duplicated nodes in mergeList
				List<Integer> finalMergeList = new ArrayList<Integer>();
				removeNodeDuplicateInRoute(mergeList, finalMergeList);

				// put the final route with no duplicated nodes to a set
				outputRoutes.add(finalMergeList);
			}

		}

	}

	/**
	 * we generate a set of combinatorial routes with condition of
	 * given max distance. It uses the combinatorial algorithm for calculation.
	 * Please refer: https://code.google.com/p/combinatoricslib/
	 * 
	 * @param inputRoutes
	 *            (input): input routes. we combine these routes to get final merged route
	 *            that satisfy the input condition (maxDistance).
	 * @param outputRoutes
	 *            (output): store the result
	 * @param numberElement
	 *            (input): number of routes of each combinatory
	 * @param maxDistance
	 *            (input): max distance of each given route
	 */
	public void CombinatoryRoutesWithDistance(Set<List<Integer>> inputRoutes, Set<List<Integer>> outputRoutes,
			Integer numberElement, Integer maxDistance) {

		// we add all routes to a map that key is route's ID (= 0,1,2...), value is the route
		HashMap<Integer, List<Integer>> allFoundRoute = new HashMap<Integer, List<Integer>>();
		List<Integer> routeID = new ArrayList<Integer>();
		Integer n = 0;
		for (List<Integer> i : inputRoutes) {
			allFoundRoute.put(n, i);
			routeID.add(n);
			n++;
		}
		// we generate set of combinatorial of routes from input "inputRoutes"
		ICombinatoricsVector<Integer> originalVector = Factory.createVector(routeID);
		Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(originalVector, numberElement);
		for (ICombinatoricsVector<Integer> perm : gen) {
			// we get each permutation (perm) and calculate distance.
			// Eg. if permutation = [0,1,2], then total distance = total distance of (route0 + route1 + route2)
			Integer totalDistance = 0;
			for (Integer j : perm) {
				totalDistance += CalculateRouteDistance(allFoundRoute.get(j));
			}
			// if totalDistance < maxDistance, we merge routes in permutation to
			// a final route
			if (totalDistance < maxDistance) {
				List<Integer> mergeList = new ArrayList<Integer>();
				for (Integer j : perm) {
					mergeList.addAll(allFoundRoute.get(j));
				}

				// remove duplicated nodes in mergeList
				List<Integer> finalMergeList = new ArrayList<Integer>();
				removeNodeDuplicateInRoute(mergeList, finalMergeList);

				// put the final route with no duplicated nodes to a set
				outputRoutes.add(finalMergeList);
			}

		}

	}

	/**
	 * find all routes from v to v with given maximum stop.
	 * 
	 * @param v
	 *            (input): starting node
	 * @param setOfRouteMaxStop
	 *            (output): store the result of all found routes
	 * @param max_stop
	 *            (input): max stop
	 * 
	 */
	public void FindAllRouteWithMaxStops(Integer v, Set<List<Integer>> setOfRouteMaxStop, Integer max_stop) {

		List<Integer> v_adj = new ArrayList<Integer>();
		/* 1. find all adjacent nodes of v that is able to move to v */
		for (Node i : graph.Nodes) {
			if (i.GetWei().get(v) != null) {
				v_adj.add(i.GetId()); // put i to list because from i we can move to v
			}
		}

		/*
		 * 2. find all routes from v to each node stored in list "v_adj" at step 1.
		 */
		foundRoutes.clear(); // clear old data first
		for (Integer i : v_adj) {
			algor.FindAllRoute(v, i);
		}
		// add v as the last stop/node of all found routes in set "foundRoutes"
		for (List<Integer> i : foundRoutes) {
			i.add(v);
		}

		Set<List<Integer>> MaxStopsRoutes = new HashSet<List<Integer>>();

		for (List<Integer> i : foundRoutes) {
			if ((i.size() - 1) <= max_stop) {
				MaxStopsRoutes.add(i);
			}
		}

		// 3. calculate maximum element of a permutation. it is max_stop / minStops;

		Integer minStops = 0;
		for (List<Integer> i : foundRoutes) {
			minStops = i.size() - 1; // take number of stop of any route
			break;
		}
		for (List<Integer> i : foundRoutes) {
			if ((i.size() - 1) < minStops) {
				minStops = i.size() - 1;
			}
		}
		Integer maxElementPermutation = max_stop / minStops;
		// save merge routes
		Set<List<Integer>> mergeRoutes = new HashSet<List<Integer>>();

		for (Integer i = 2; i <= maxElementPermutation; i++) {
			CombinatoryRoutesWithNumberNodes(MaxStopsRoutes, mergeRoutes, i, max_stop);
		}

		/*
		 * 4. collect all routes in the set "foundRoutes" that has maximum 3
		 * stops
		 */

		// collect from original route that distance lesser than maxDistance
		for (List<Integer> i : MaxStopsRoutes) {
			setOfRouteMaxStop.add(i);

		}
		// collect from combinatory route that total stops lesser than max_stop
		for (List<Integer> i : mergeRoutes) {
			setOfRouteMaxStop.add(i);

		}
	}

	/**
	 * remove duplicated nodes in a route.
	 * For example: "0->1->1->2" will be converted to "0->1->2".
	 */
	public void removeNodeDuplicateInRoute(List<Integer> duplicateRoute, List<Integer> noDuplicateRoute) {

		if ((duplicateRoute != null) && (noDuplicateRoute != null)) {

			for (Integer k = 0; k < duplicateRoute.size() - 1; k++) {
				if (duplicateRoute.get(k) != duplicateRoute.get(k + 1)) {
					noDuplicateRoute.add(duplicateRoute.get(k));
				}
				if (k == duplicateRoute.size() - 2) {
					noDuplicateRoute.add(duplicateRoute.get(k + 1)); // add last node of merge list to final list
				}
			}
		}
	}

	/**
	 * Calculate distance of a given path.
	 * 
	 * @param strPath
	 *            given route. It is a string of integer number. for example:
	 *            "012", "0123"
	 * @return distance of route or zero if path is unreachable.
	 */
	public Integer CalculateDistanceWithStringPath(String strPath) {
		List<Integer> path = new ArrayList<Integer>();
		Integer distance = 0;

		if (strPath != null) {
			for (int i = 0; i < strPath.length(); i++) {
				String str = strPath.substring(i, i + 1);
				path.add(Integer.parseInt(str));
			}
		}

		if (path.size() >= 2) {
			distance = CalculateRouteDistance(path);
		}

		return distance;
	}

	/**
	 * Implementation of question 1.
	 * 
	 * The distance of the route A-B-C. (= 9)
	 */
	public void FindAllRouteWithCondition1(String strPath) {
		System.out.println("\n=======Start calculating for question 1=======");
		Integer distance = 0;
		distance = CalculateDistanceWithStringPath(strPath);

		/* print the final result */
		if (distance > 0) {
			System.out.println("Output#1: " + distance);
		} else {
			System.out.println("Output#1: NO SUCH ROUTE.");
		}

	}

	/**
	 * Implementation of question 2.
	 * 
	 * The distance of the route A-D. (=5)
	 */
	public void FindAllRouteWithCondition2(String strPath) {
		System.out.println("\n=======Start calculating for question 2=======");
		Integer distance = 0;
		distance = CalculateDistanceWithStringPath(strPath);

		/* print the final result */
		if (distance > 0) {
			System.out.println("Output#2: " + distance);
		} else {
			System.out.println("Output#2: NO SUCH ROUTE.");
		}

	}

	/**
	 * Implementation of question 3.
	 * 
	 * The distance of the route A-D-C.(=13)
	 */
	public void FindAllRouteWithCondition3(String strPath) {
		System.out.println("\n=======Start calculating for question 3=======");
		Integer distance = 0;
		distance = CalculateDistanceWithStringPath(strPath);

		/* print the final result */
		if (distance > 0) {
			System.out.println("Output#3: " + distance);
		} else {
			System.out.println("Output#3: NO SUCH ROUTE.");
		}
	}

	/**
	 * Implementation of question 4.
	 * 
	 * The distance of the route A-E-B-C-D.(=22)
	 */
	public void FindAllRouteWithCondition4(String strPath) {
		System.out.println("\n=======Start calculating for question 4=======");
		Integer distance = 0;
		distance = CalculateDistanceWithStringPath(strPath);

		/* print the final result */
		if (distance > 0) {
			System.out.println("Output#4: " + distance);
		} else {
			System.out.println("Output#4: NO SUCH ROUTE.");
		}
	}

	/**
	 * Implementation of question 5.
	 * 
	 * The distance of the route A-E-D. (NO SUCH ROUTE)
	 */
	public void FindAllRouteWithCondition5(String strPath) {
		System.out.println("\n=======Start calculating for question 5=======");
		Integer distance = 0;
		distance = CalculateDistanceWithStringPath(strPath);

		/* print the final result */
		if (distance > 0) {
			System.out.println("Output#5: " + distance);
		} else {
			System.out.println("Output#5: NO SUCH ROUTE.");
		}
	}

	/**
	 * Implementation of question 6.
	 * 
	 * The number of trips starting at C and ending at C with a maximum of 3
	 * stops. In the sample data below, there are two such trips: C-D-C (2
	 * stops). and C-E-B-C (3 stops).
	 * 
	 * @param v
	 *            (input): starting node
	 * @param max_stop
	 *            (input): number of max stop
	 */
	public Integer FindAllRouteWithCondition6(Integer v, Integer max_stop) {

		System.out.println("\n=======Start calculating for question 6=======");

		List<Integer> v_adj = new ArrayList<Integer>();
		/* 1. find all adjacent nodes of v that is able to move to v */
		for (Node i : graph.Nodes) {
			if (i.GetWei().get(v) != null) {
				v_adj.add(i.GetId()); // put i to list because from this node i, we can move to v
			}
		}

		/*
		 * 2. find all routes from v to each node stored in list "v_adj" at step 1.
		 */
		foundRoutes.clear(); // clear old data first
		for (Integer i : v_adj) {
			algor.FindAllRoute(v, i);
		}
		// add v as the last stop/node of all found routes in set "foundRoutes"
		for (List<Integer> i : foundRoutes) {
			i.add(v);
		}

		Set<List<Integer>> MaxStopsRoutes = new HashSet<List<Integer>>();
		for (List<Integer> i : foundRoutes) {
			if ((i.size() - 1) <= max_stop) {
				MaxStopsRoutes.add(i);
			}
		}

		// 3. calculate maximum element of a permutation. it is max_stop / minStops;
		Integer minStops = 0;
		for (List<Integer> i : foundRoutes) {
			minStops = i.size() - 1; // take number of stop of any route
			break;
		}
		for (List<Integer> i : foundRoutes) {
			if ((i.size() - 1) < minStops) {
				minStops = i.size() - 1;
			}
		}

		Integer maxElementPermutation = 0;
		if (minStops != 0) {
			maxElementPermutation = max_stop / minStops;
		}
		// save merge routes
		Set<List<Integer>> mergeRoutes = new HashSet<List<Integer>>();

		for (Integer i = 2; i <= maxElementPermutation; i++) {
			CombinatoryRoutesWithNumberNodes(MaxStopsRoutes, mergeRoutes, i, max_stop);
		}

		/*
		 * 4. collect all routes in the set "foundRoutes" that has maximum 3
		 * stops
		 */
		Integer numRouteWithMaxStop = 0;
		System.out.println("Collect the result:");

		// collect from original route that distance lesser than maxDistance
		for (List<Integer> i : MaxStopsRoutes) {
			numRouteWithMaxStop++;
			String str = "";
			for (Integer n : i) { // just for print info
				str += n + " -> ";
			}
			// just remove the last arrow "->" and print it
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
		}
		// collect from combinatory route that total stops lesser than max_stop
		for (List<Integer> i : mergeRoutes) {
			numRouteWithMaxStop++;
			String str = "";
			for (Integer n : i) { // just for print info
				str += n + " -> ";
			}
			// just remove the last arrow "->" and print it
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
		}

		/* 5. print the final result */
		if (numRouteWithMaxStop > 0) {
			System.out.println("Output#6: " + numRouteWithMaxStop);
		} else {
			System.out.println("Output#6: NO SUCH ROUTE.");
		}

		// return for support unit test
		return numRouteWithMaxStop;

	}

	/**
	 * Implementation of question 7.
	 * 
	 * The number of trips starting at A and ending at C with exactly 4 stops.
	 * In the sample data below, there are three such trips: A to C (via B,C,D);
	 * A to C (via D,C,D); and A to C (via D,E,B).
	 * 
	 * @param src
	 *            source node
	 * @param des
	 *            destination node
	 * @param numOfStop
	 *            (input): number of max stops
	 */
	public Integer FindAllRouteWithCondition7(Integer s, Integer d, Integer numOfStop) {

		System.out.println("\n=======Start calculating for question 7=======");
		foundRoutes.clear();
		/*
		 * we design 4 sets of route: one set stores all routes from s to d with
		 * maximum stop = (numOfStop - 2). One set store all routes from d to d
		 * with maximum stop = (numOfStop - 2). One set stores all routes from s
		 * to d with exactly number of stop = numOfStop. The last one is final
		 * set to merge routes from above 2 sets
		 */
		Set<List<Integer>> srcToDesWithMaxStop = new HashSet<List<Integer>>();
		Set<List<Integer>> desToDesWithMaxStop = new HashSet<List<Integer>>();
		Set<List<Integer>> srcToDesWithNumStop = new HashSet<List<Integer>>();
		Set<List<Integer>> mergeRoute = new HashSet<List<Integer>>();

		/* 1. find all routes from s to d */
		algor.FindAllRoute(s, d);

		/*
		 * 2. collect all routes in the set "foundRoutes" that has number of
		 * stop = "numOfStop".
		 */
		for (List<Integer> i : foundRoutes) {
			if (i.size() == numOfStop + 1) {
				srcToDesWithNumStop.add(i);
			}
		}

		/*
		 * 3. collect all routes in the set "foundRoutes" that has number of
		 * stop < (numOfStop - 2)
		 */
		for (List<Integer> i : foundRoutes) {
			if ((i.size() - 1) <= (numOfStop - 2)) { // (i.size() - 1) = number of stop of route
				srcToDesWithMaxStop.add(i);
			}
		}

		/*
		 * 4. we find all routes from d to d with maximum stop = (numOfStop - 2).
		 * It is similar in question 6
		 */
		Set<List<Integer>> setOfRouteWithMaxStop = new HashSet<List<Integer>>();
		FindAllRouteWithMaxStops(d, setOfRouteWithMaxStop, numOfStop - 2);
		for (List<Integer> i : setOfRouteWithMaxStop) {
			desToDesWithMaxStop.add(i);
		}

		/*
		 * 5. we combine the routes in "srcToDesWithMaxStop" and the routes in
		 * "desToDesWithMaxStop". For sample, we don't use combinatorial
		 * algorithm here. But will use combinatorial algorithm in question 10
		 * with a library.
		 */
		for (List<Integer> i : srcToDesWithMaxStop) {
			for (List<Integer> j : desToDesWithMaxStop) {
				if (((i.size() - 1) + (j.size() - 1)) == numOfStop) {
					List<Integer> mergeList = new ArrayList<Integer>();
					List<Integer> finalMergeList = new ArrayList<Integer>();
					mergeList.addAll(i);
					mergeList.addAll(j);
					// remove duplicated nodes in mergeList
					removeNodeDuplicateInRoute(mergeList, finalMergeList);
					mergeRoute.add(finalMergeList);
				}
			}
		}

		/*
		 * 6. put all found routes to global variable foundRoutes
		 */
		foundRoutes.clear();
		for (List<Integer> i : srcToDesWithNumStop) {
			foundRoutes.add(i);
		}
		for (List<Integer> i : mergeRoute) {
			foundRoutes.add(i);
		}

		System.out.println("Collect the result:");
		Integer pathWithNumberOfStop = 0;

		for (List<Integer> i : foundRoutes) {
			pathWithNumberOfStop++;
			String str = "";
			for (Integer n : i) { // just for print info
				str += n + " -> ";
			}
			// just remove the last arrow "->" and print it
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
		}

		/* 7. print the final result */
		if (pathWithNumberOfStop > 0) {
			System.out.println("Output#7: " + pathWithNumberOfStop);
		} else {
			System.out.println("Output#7: NO SUCH ROUTE.");
		}

		// return for support unit test
		return pathWithNumberOfStop;
	}

	/**
	 * Implementation of question 8.
	 * 
	 * The length of the shortest route (in terms of distance to travel) from A
	 * to C.
	 * 
	 * @param src
	 *            source node
	 * @param des
	 *            destination node
	 */
	public Integer FindAllRouteWithCondition8(Integer src, Integer des) {
		System.out.println("\n=======Start calculating for question 8=======");

		List<Integer> foundPath = new ArrayList<Integer>();
		int shortestDistance = 0;
		shortestDistance = algor.Dijkstra(src, des, foundPath);

		System.out.println("Collect the result:");
		String str = "";
		for (Integer n : foundPath) { // just for print info
			str += n + " -> ";
		}
		// just remove the last arrow "->" and print it
		if (foundPath.size() != 0) {
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
			System.out.println("Output#8: " + shortestDistance);
		} else {
			System.out.println("Output#8: NO SUCH ROUTE");
		}

		// return for support unit test
		return shortestDistance;
	}

	/**
	 * Implementation of question 9.
	 * 
	 * The length of the shortest route (in terms of distance to travel) from B
	 * to B.
	 * 
	 * @param v
	 *            (input): starting node
	 */
	public Integer FindAllRouteWithCondition9(Integer v) {

		System.out.println("\n=======Start calculating for question 9=======");
		// shortest distance from B to B =
		// Min(shortest distance from B to adjacent node of B + distance from
		// adjacent node of B to B)

		List<Integer> v_adj = new ArrayList<Integer>();
		/* 1. find all adjacent nodes of v that is able to move to v */
		for (Node i : graph.Nodes) {
			if (i.GetWei().get(v) != null) {
				v_adj.add(i.GetId()); // put i to list because from this node i, we can move to v
			}
		}

		/*
		 * 2. find shortest distance from v to each node stored in list "v_adj"
		 * at step 1
		 */
		List<Integer> foundPathTemp = new ArrayList<Integer>();
		List<Integer> foundPath = new ArrayList<Integer>();
		Integer shortestDistanceAdj = 0;
		Integer shortestDistance = 0;
		Integer minDistance = Integer.MAX_VALUE;

		if (v_adj != null) {
			for (Integer i : v_adj) {
				foundPathTemp.clear();
				shortestDistanceAdj = algor.Dijkstra(v, i, foundPathTemp);
				if (shortestDistanceAdj == 0) { // no route from v to i
					continue;
				}
				shortestDistance = shortestDistanceAdj + graph.Nodes.get(i).GetWei().get(v);
				if (minDistance > shortestDistance) {
					foundPath.clear();
					minDistance = shortestDistance;
					foundPath.addAll(foundPathTemp);
					foundPath.add(v); // add v as last node
				}
			}

		}

		System.out.println("Collect the result:");
		String str = "";
		for (Integer n : foundPath) { // just for print info
			str += n + " -> ";
		}
		if (foundPath.size() != 0) {
			// just remove the last arrow "->" and print it
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
			System.out.println("Output#9: " + minDistance);
		} else {
			System.out.println("Output#9: NO SUCH ROUTE");
		}

		// return for support unit test
		return minDistance;
	}

	/**
	 * Implementation of question 10.
	 * 
	 * The number of different routes from C to C with a distance of less than
	 * 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC,
	 * CEBCEBC, CEBCEBCEBC.
	 * 
	 * @param v
	 *            (input): starting node
	 * @param maxDistance
	 *            (input): max distance
	 */
	public Integer FindAllRouteWithCondition10(Integer v, Integer maxDistance) {

		System.out.println("\n=======Start calculating for question 10=======");

		List<Integer> v_adj = new ArrayList<Integer>();
		/* 1. find all adjacent nodes of v that is able to move to v */
		for (Node i : graph.Nodes) {
			if (i.GetWei().get(v) != null) {
				v_adj.add(i.GetId()); // put i to list because from i we can
										// move to v
			}
		}

		/*
		 * 2. find all routes from v to each node stored in list "v_adj" at step 1.
		 */
		foundRoutes.clear(); // clear old data first
		for (Integer i : v_adj) {
			algor.FindAllRoute(v, i);
		}

		/*
		 * 3. Because all routes inside the set "foundRoutes" does not include
		 * node v as the last stop/node of the route, so we need add v to each
		 * route for calculate distance
		 */
		for (List<Integer> i : foundRoutes) {
			i.add(v);
		}

		/*
		 * 4. find all routes in the set "foundRoutes" that has distance lesser
		 * than input "maxDistance" then put them to an other set
		 * "distanceRoutes"
		 */

		Set<List<Integer>> distanceRoutes = new HashSet<List<Integer>>();
		for (List<Integer> i : foundRoutes) {
			if (CalculateRouteDistance(i) < maxDistance) {
				distanceRoutes.add(i);
			}
		}

		/*
		 * 5. Now, we need do a combinatorial algorithm to combine the routes
		 * found in set "distanceRoutes" that total distance is lesser than the
		 * input "maxDistance". Because combinatorial algorithm is big and time
		 * limitation :)) I used a library for combinatorial algorithm from:
		 * https://code.google.com/p/combinatoricslib/ Number of element of a
		 * permutation is increasing from 2 -> (maxDistance/shortest_route) of
		 * the set "distanceRoutes"
		 */

		// a. find shortest route in set "distanceRoutes"
		Integer shortestDistance = 0;
		for (List<Integer> i : distanceRoutes) {
			shortestDistance = CalculateRouteDistance(i); // take length of any route.
			break;
		}
		for (List<Integer> i : distanceRoutes) {
			if (CalculateRouteDistance(i) < shortestDistance) {
				shortestDistance = CalculateRouteDistance(i);
			}
		}
		// b. calculate maximum element of a permutation. it is
		// maxDistance/shortestDistance
		Integer maxElementPermutation = 0;
		if (shortestDistance != 0) {
			maxElementPermutation = maxDistance / shortestDistance;
		}

		// c. now, we combine all routes in set "distanceRoutes" into
		// permutations that number of element of each
		// permutation to be increased from 2 to maxElementPermutation
		Set<List<Integer>> mergeRoutes = new HashSet<List<Integer>>(); // save merged routes

		for (Integer i = 2; i <= maxElementPermutation; i++) {

			CombinatoryRoutesWithDistance(distanceRoutes, mergeRoutes, i, maxDistance);
		}

		/* 6. print the final result */
		System.out.println("Collect the result:");
		Integer numRouteWithMaxDistance = 0;
		// collect from original route that distance lesser than maxDistance
		for (List<Integer> i : distanceRoutes) {
			numRouteWithMaxDistance++;
			String str = "";
			for (Integer n : i) { // just for print info
				str += n + " -> ";
			}
			// just remove the last arrow "->" and print it
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
		}
		// collect from combinatory routes that total distance lesser than
		// maxDistance
		for (List<Integer> i : mergeRoutes) {
			numRouteWithMaxDistance++;
			String str = "";
			for (Integer n : i) { // just for print info
				str += n + " -> ";
			}
			// just remove the last arrow "->" and print it
			graphUserView.printNodesByCharName(str.substring(0, str.length() - 3));
		}

		if (numRouteWithMaxDistance > 0) {
			System.out.println("Output#10: " + numRouteWithMaxDistance);
		} else {
			System.out.println("Output#10: NO SUCH ROUTE.");
		}

		// return for support unit test
		return numRouteWithMaxDistance;
	}
}
