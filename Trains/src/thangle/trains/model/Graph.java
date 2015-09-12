/**
 * 
 */
package thangle.trains.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thang Le
 */
public class Graph {

	public Integer V; // Number of nodes of the graph
	public List<Node> Nodes; // To store all nodes of the graph

	// Constructor
	public Graph(Integer V) {
		this.V = V;
		// Initialize Nodes
		Nodes = new ArrayList<Node>(V);
		for (int i = 0; i < V; i++) {
			Nodes.add(new Node());
		}
	}

}
