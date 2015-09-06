/**
 * 
 */
package thangle.trains.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import thangle.trains.controller.GraphController;
import thangle.trains.model.Graph;

/**
 * @author Thang Le
 * 
 *         Implementation of Dijkstra algorithm
 *         and Depth First Search algorithm
 *
 */
public class Algorithms {
	Graph graph; // a graph 

	public Algorithms(Graph g) {
		this.graph = g;
	}

	/**
	 * Depth First Search algorithm.
	 * 
	 * @param u
	 *            source node
	 * @param d
	 *            destination node
	 * @param visited
	 *            store nodes that visited
	 * @param path_index
	 *            index of the current visited node
	 */
	void DFSUtil(Integer u, Integer d, List<Boolean> visited, List<Integer> path, Integer path_index) {
		// Mark the current node as visited and add to path
		visited.set(u, new Boolean(true));
		path.add(path_index, u);
		path_index++;

		if (u == d) {
			String str = "";
			List<Integer> curr_path = new ArrayList<Integer>();
			for (Integer i = 0; i < path_index; i++) {
				curr_path.add(path.get(i)); // save current found path to a list
				str += path.get(i) + " -> ";
			}
			// just remove the last arrow "->"
			System.out.println(str.substring(0, str.length() - 3));

			GraphController.foundRoutes.add(curr_path); // save to a global set

		} else {
			// Recur for all the vertices adjacent to this node
			for (Integer i : graph.Nodes.get(u).GetAdj()) {
				if (visited.get(i) == false) {
					DFSUtil(i, d, visited, path, path_index);
				}
			}
		}

		path_index--;
		visited.set(u, new Boolean(false));
	}

	/**
	 * Find all routes possible from s to d. It uses the Depth First Search algorithm.
	 * 
	 * @param s
	 *            source node
	 * 
	 * @param d
	 *            destination node
	 * 
	 */
	public void FindAllRoute(Integer s, Integer d) {
		Integer path_index = 0;

		List<Integer> path = new ArrayList<Integer>(graph.V);

		// Mark all the vertices as not visited
		List<Boolean> visited = new ArrayList<Boolean>(graph.V);
		for (Integer i = 0; i < graph.V; i++) {
			visited.add(new Boolean(false));
		}

		// Call the recursive helper function to print DFS traversal
		DFSUtil(s, d, visited, path, path_index);
	}

	/**
	 * A utility function of Dijkstra algorithm to find the vertex with minimum
	 * distance value, from the set of vertices not yet included in shortest
	 * path tree
	 */
	public int minDistanceDijkstra(List<Integer> dist, List<Boolean> sptSet) {
		// Initialize min value
		Integer min = Integer.MAX_VALUE;
		Integer min_index = 0;

		for (Integer v = 0; v < graph.V; v++) {
			if ((sptSet.get(v) == false) && (dist.get(v) <= min)) {
				min = dist.get(v);
				min_index = v;
			}
		}

		return min_index;
	}

	/**
	 * Dijkstra algorithm. Function that implements Dijkstra's single source
	 * shortest path algorithm for a graph represented using adjacency matrix
	 * representation.
	 * 
	 * @param foundPath
	 *            (output) the shortest path from src to des
	 * @param src
	 *            source node
	 * @param des
	 *            destination node
	 * @return the shortest distance
	 * 
	 */
	public Integer Dijkstra(Integer src, Integer des, List<Integer> foundPath) {
		List<Integer> dist = new ArrayList<Integer>(graph.V); // The output array.
		// dist[i] will hold the shortest distance from src to i

		List<Boolean> sptSet = new ArrayList<Boolean>(graph.V); // sptSet[i] will true

		// if vertex i is included in shortest
		// path tree or shortest distance from src to i is finalized
		List<Integer> parentNodes = new ArrayList<Integer>();

		// Initialize all distances as INFINITE and stpSet[] as false
		for (Integer i = 0; i < graph.V; i++) {
			dist.add(Integer.MAX_VALUE);
			sptSet.add(false);
			parentNodes.add(Integer.MAX_VALUE);
		}

		// Distance of source vertex from itself is always 0
		dist.set(src, 0);

		// Find shortest path for all vertices
		for (Integer count = 0; count < graph.V - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices not
			// yet processed. u is always equal to src in first iteration.
			int u = minDistanceDijkstra(dist, sptSet);

			// Mark the picked vertex as processed
			sptSet.set(u, true);

			// Update dist value of the adjacent vertices of the picked vertex.
			for (Integer v = 0; v < graph.V; v++) {

				// Update dist[v] only if is not in sptSet, there is an edge from
				// u to v, and total weight of path from src to v through u is
				// smaller than current value of dist[v]
				Integer tmp = graph.Nodes.get(u).GetWei().get(v);
				if (tmp == null)
					tmp = 0;

				if ((!sptSet.get(v) && (tmp > 0) && (dist.get(u) != Integer.MAX_VALUE)
						&& (dist.get(u) + tmp < dist.get(v)))) {

					dist.set(v, dist.get(u) + tmp);
					parentNodes.set(v, u);
				}
			}
		}

		// process to find path by go revert from des to src. like linked list
		Integer current = des;
		Integer prev = des;
		Integer shortestDistance = 0;
		if (parentNodes.get(des) != Integer.MAX_VALUE) {
			while (true) {
				if (current == des) {
					foundPath.add(current);
					prev = parentNodes.get(current);
				}
				if (prev == src) {
					foundPath.add(src);
					break;
				}
				//
				current = prev;
				foundPath.add(current);
				prev = parentNodes.get(current);
			}
			// reverse list
			Collections.reverse(foundPath);
			shortestDistance = dist.get(des);
		}

		return shortestDistance;
	}

}
