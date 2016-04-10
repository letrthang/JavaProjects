package thangle.trains.view;

import java.util.ArrayList;
import java.util.List;

import thangle.trains.controller.GraphController;

public class GraphUserView {
	GraphController graphController;

	public GraphUserView() { 

	}

	public GraphUserView(GraphController graphCont) {
		graphController = graphCont;
	}

	/**
	 * print a route.
	 * To covert node of route from a number's format to a character's format.
	 * For example: input route = "0->1->2" then will be printed as "A->B->C".
	 */
	public void printNodesByCharName(String nodesByNumber) {

		String nodesByName = "";
		List<String> route = new ArrayList<String>();
		int ascii = 0;
		nodesByNumber = nodesByNumber.trim();

		for (int i = 0; i < nodesByNumber.length(); i++) {
			ascii = nodesByNumber.charAt(i);
			if ((ascii >= 48) && (ascii <= 57)) { // ascii code of 0->9
				nodesByName += Character.toString((char) ascii);
			}

			if (ascii == 62) { // ascii code of >
				nodesByName = nodesByName.trim();
				route.add(nodesByName);
				nodesByName = "";
			}
			// process last node
			if (i == nodesByNumber.length() - 1) { //
				nodesByName = nodesByName.trim();
				route.add(nodesByName);
			}
		}

		int asciiCode = 0;
		String routeByName = "";

		for (String nodeNumber : route) {
			asciiCode = Integer.parseInt(nodeNumber) + 65;
			routeByName += Character.toString((char) asciiCode) + " -> ";
		}
		System.out.println(routeByName.substring(0, routeByName.length() - 3));// just remove the last arrow "->"

	}

	/**
	 * To calculate result based on user input data which user stores in text file.
	 * So, it does not interact with user as a real GUI.
	 * */
	public void calculateUserRequests() {

		/*
		 * The distance of the route A-B-C.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition1("012"); // A-B-C. (= 9)

		/*
		 * The distance of the route A-D.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition2("03"); // A-D. (=5)

		/*
		 * The distance of the route A-D-C.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition3("032"); // A-D-C.(=13)

		/*
		 * The distance of the route A-E-B-C-D.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition4("04123"); // A-E-B-C-D.(=22)

		/*
		 * The distance of the route A-E-D.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition5("043"); // A-E-D. (NO SUCH ROUTE)

		/*
		 * The number of trips starting at C and ending at C with a maximum of 3 stops.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition6(2, 3); // C->C with max 3 stops (=2)

		/*
		 * The number of trips starting at A and ending at C with exactly 4 stops.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition7(0, 2, 4); // A->C with exact 4 stops (=3)

		/*
		 * The length of the shortest route (in terms of distance to travel) from A to C
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition8(0, 2); // Min distance from A->C (=9)

		/*
		 * The length of the shortest route (in terms of distance to travel) from B to B.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition9(1); // Min distance from B->B (=9)

		/*
		 * The number of different routes from C to C with a distance of less than 30.
		 * Note: A=0, B=1, C=2, D=3, E=4.
		 */
		graphController.FindAllRouteWithCondition10(2, 30); // C->C with max distance = 30 (=7)

	}

}
