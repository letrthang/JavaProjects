/**
 * 
 */
package thangle.trains.model;

import java.util.List;

/**
 * @author thang.le
 *
 */
public class GraphDAOImpl implements GraphDAO {
	Graph graph;

	public GraphDAOImpl(Graph g) {
		graph = g;
	}

	/**
	 * Function to add an edge to the graph. For example: (v=0, a=1, wei = 5)
	 * <=> "AB5", (v=0, a=2, wei = 4) <=> "AC4", ...
	 * 
	 * @param v
	 *            start node
	 * @param a
	 *            end node
	 * @param wei
	 *            weight of edge
	 */
	public void addEdge(Integer v, Integer a, Integer wei) {
		graph.Nodes.get(v).SetId(v); // set ID for a node
		graph.Nodes.get(v).setAdj(a); // add its adjacent node id
		graph.Nodes.get(v).setWei(a, wei); // set weight of edge "v->a"
	}

	/**
	 * Add a list of Edge to graph
	 * 
	 * @param edgeList
	 *            List of edges is read from input text file
	 */
	public void addEdgeList(List<Edge> edgeList) {

		if (edgeList != null) {
			for (Edge edge : edgeList) {
				addEdge(edge.getStartNode(), edge.getEndNode(), edge.getWeight());
			}
		}
	}

}
