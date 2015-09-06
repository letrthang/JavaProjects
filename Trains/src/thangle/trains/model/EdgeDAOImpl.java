/**
 * 
 */
package thangle.trains.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Thang Le
 */
public class EdgeDAOImpl implements EdgeDAO{

	/**
	 * Read data of graph from a formatted text file and covert them to Edge objects.
	 * 
	 * @return List<Edge> : list of edges
	 * */
	public List<Edge> readEdge(String inputFile) {
		String inputGraph = "";
		String nodeNamebyChar = "";

		List<Edge> edgeList = new ArrayList<Edge>();
		try {
			inputGraph = new String(Files.readAllBytes(Paths.get(inputFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputGraph.toUpperCase();
		inputGraph.trim();
		System.out.println("Read input graph: " + inputGraph);

		for (int i = 0; i < inputGraph.length(); i++) {
			if (inputGraph.charAt(i) != ",".charAt(0)) {
				nodeNamebyChar += inputGraph.substring(i, i + 1);
			} else {
				nodeNamebyChar = nodeNamebyChar.trim();
				if (nodeNamebyChar.length() == 0) {
					System.out.println("Warning: wrong input format");
				} else {
					edgeList.add(this.convertToEdge(nodeNamebyChar));
				}
				nodeNamebyChar = "";
			}
			// process the last edge of the graph
			if (i == inputGraph.length() - 1) {
				nodeNamebyChar = nodeNamebyChar.trim();
				if (nodeNamebyChar.length() == 0) {
					System.out.println("Warning: wrong input format");
				} else {
					edgeList.add(this.convertToEdge(nodeNamebyChar));
				}
			}
		}

		return edgeList;

	}

	/**
	 * Convert an edge by characters to numbers and then put it to an Edge.
	 * For example: "AB5" will be converted to "015". So, Edge = (0,1,5). 
	 * "AZ6" will be converted to "0256". So, Edge = (0,25,6). 
	 * 
	 * */
	public Edge convertToEdge(String nodeNamebyChar) {

		Edge edge = new Edge();
		String weight = "";
		int assci = 0;

		assci = nodeNamebyChar.charAt(0);
		if ((assci >= 65) && (assci <= 90)) {
			assci = assci - 65; // now "A"=0, "B"=1, "C"=2,...
			edge.setStartNode(assci);
		}

		assci = nodeNamebyChar.charAt(1);
		if ((assci >= 65) && (assci <= 90)) {
			assci = assci - 65; // now "A"=0, "B"=1, "C"=2,...
			edge.setEndNode(assci);
		}

		weight = nodeNamebyChar.substring(2, nodeNamebyChar.length());
		edge.setWeight(Integer.parseInt(weight.replaceAll("[\\D]", "")));

		return edge;
	}

	/**
	 * get number of nodes of a given graph in input text file
	 * @return number of nodes of the graph.
	 * */
	public  int readGraphSize(String inputFile) {

		int graphSize = 0;
		String inputGraph = "";
		Set<Character> graphSizeSet = new HashSet<>();

		try {
			inputGraph = new String(Files.readAllBytes(Paths.get(inputFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		inputGraph.toUpperCase();
		inputGraph.trim();

		// graph size
		char assci = 0;
		for (int i = 0; i < inputGraph.length(); i++) {
			assci = inputGraph.charAt(i);
			if ((assci >= 65) && (assci <= 90)) {
				graphSizeSet.add(Character.valueOf(assci));
			}
		}
		char nodebyChar = 65; // "A"
		for (char ch : graphSizeSet) {
			if (ch > nodebyChar) {
				nodebyChar = ch;
			}
		}

		int nodeMax = (int) (nodebyChar - 65);
		if (nodeMax != graphSizeSet.size() - 1) {
			graphSize = 0;
			System.out.println("Input data is incorrect. Node name only to be increased one by one.");
			System.out.println("Pls change node \"" + nodebyChar + "\" to correct name. Refer below example.");
			System.out.println("Eg1. AB1, BC2, CD3. -> 4 nodes corresponding with A, B, C, D -> OK");
			System.out.println("Eg2. AB1, BC2, CE3. -> 4 nodes corresponding with A, B, C, E -> NOT OK");
		} else {
			graphSize = graphSizeSet.size();
		}

		return graphSize;

	}
}
