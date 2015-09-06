/**
 * 
 */
package thangle.trains.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import thangle.trains.controller.GraphController;
import thangle.trains.model.Graph;
import thangle.trains.model.GraphDAO;
import thangle.trains.model.GraphDAOImpl;

/**
 * @author Thang Le
 */
public class AllTest {
	Graph g;
	GraphDAO graphDAO ;
	GraphController graphController;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Manually create a graph for unit test
		// AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		// A=0, B=1, C=2, D=3, E=4.
		g = new Graph(5);
		graphDAO = new GraphDAOImpl(g);
		graphController = new GraphController(g);
		
		graphDAO.addEdge(0, 1, 5); // AB5
		graphDAO.addEdge(0, 3, 5); // AD5
		graphDAO.addEdge(0, 4, 7); // AE7
		graphDAO.addEdge(1, 2, 4); // BC4
		graphDAO.addEdge(2, 3, 8); // CD8
		graphDAO.addEdge(2, 4, 2); // CE2
		graphDAO.addEdge(3, 2, 8); // DC8
		graphDAO.addEdge(3, 4, 6); // DE6
		graphDAO.addEdge(4, 1, 3); // EB3
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		g = null;
		graphDAO = null;
		graphController = null;
	}

	/* we assign node name by a character to node name by an integer number.
	 * Starting by 0 to 25 corresponding with "A" to "Z". So, in this exercise,
	 * the mapping is: A=0, B=1, C=2, D=3, E=4. 
	 */			
	
	/**
	 * Test for question 1 to 5
	 * */
	@Test
	public void testCalculateDistanceWithStringPath() {
		int distance = 0;
		distance = graphController.CalculateDistanceWithStringPath("012"); // A-B-C. (= 9)
		assertEquals("Distance A-B-C must be 9", 9, distance);
		distance = graphController.CalculateDistanceWithStringPath("043"); // A-E-D. (= 0)
		assertEquals("Distance A-B-C must be 0", 0, distance);
	}

	/**
	 * Test for question 6
	 * */
	@Test
	public void testFindAllRouteWithCondition6() {
		int result = 0;
		result = graphController.FindAllRouteWithCondition6(2, 3); //
		assertEquals("Number of route must be 2", 2, result);
	}

	/**
	 * Test for question 7
	 * */
	@Test
	public void testFindAllRouteWithCondition7() {
		int result = 0;
		result = graphController.FindAllRouteWithCondition7(0, 2, 4); //
		assertEquals("result must be 3", 3, result);
	}

	/*
	 * Test for question 8
	 * */
	@Test
	public void testFindAllRouteWithCondition8() {
		int result = 0;
		result = graphController.FindAllRouteWithCondition8(0, 2); //
		assertEquals("result must be 9", 9, result);
	}

	/**
	 * Test for question 9
	 * */
	@Test
	public void testFindAllRouteWithCondition9() {
		int result = 0;
		result = graphController.FindAllRouteWithCondition9(1); //
		assertEquals("result must be 9", 9, result);
	}

	/**
	 * Test for question 10
	 * */
	@Test
	public void testFindAllRouteWithCondition10() {
		int result = 0;
		result = graphController.FindAllRouteWithCondition10(2, 30); //
		assertEquals("result must be 7", 7, result);
	}
}
