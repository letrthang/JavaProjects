/**
 * {@link} www.letrungthang.blogspot.com
 * 
 * @author Thang Le. (letrthang@gmail.com).
 */

package thangle.trains.main;

import java.util.ArrayList;
import java.util.List;

import thangle.trains.controller.GraphController;
import thangle.trains.model.Edge;
import thangle.trains.model.EdgeDAO;
import thangle.trains.model.EdgeDAOImpl;
import thangle.trains.model.Graph;
import thangle.trains.model.GraphDAO;
import thangle.trains.model.GraphDAOImpl;
import thangle.trains.view.GraphUserView;

/**
 * @author Thang Le. (letrthang@gmail.com).
 *         {@link} www.letrungthang.blogspot.com
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * 1. Initialize the resource file
		 */
		String GraphFile = "resource/GraphData.txt";

		/*
		 * 2. Initialize the Model
		 */
		List<Edge> edgeList = new ArrayList<Edge>();
		EdgeDAO edgeDAO = new EdgeDAOImpl();

		Graph graph;
		GraphDAO graphDAO;
		int graphSize = 0;

		/*
		 * Read graph data from input text file.
		 * The graph is either directed or undirected graph.
		 */
		edgeList = edgeDAO.readEdge(GraphFile);
		graphSize = edgeDAO.readGraphSize(GraphFile);

		if ((graphSize != 0) && (edgeList.size() != 0)) {
			// Create a graph given in the above input data
			graph = new Graph(graphSize);
			graphDAO = new GraphDAOImpl(graph);

			/*
			 * We assign each node's name by character gotten from input text file to node's name by number.
			 * Starting by 0 to 25 corresponding with "A" to "Z". So, in this exercise,
			 * the mapping is: A=0, B=1, C=2, D=3, E=4.
			 * So, an edge is presented by characters also is converted to numbers for programming convenient.
			 * For example: "AB5" will be converted to "015". "BC4" will be converted to "124".
			 */
			graphDAO.addEdgeList(edgeList);

			/*
			 * 3. Initialize the Controller
			 */
			GraphController graphController = new GraphController(graph);
			/*
			 * 4. Initialize the View
			 */
			GraphUserView graphUserView = new GraphUserView(graphController);
			/*
			 * 5. Execution of View
			 */
			graphUserView.calculateUserRequests();
		}
	}
}
