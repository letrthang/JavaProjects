package thangle.trains.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Thang Le
 */
public class Node {

	Integer Id; // Node id
	List<Integer> adj; // An array containing list of adjacency nodes of this node
	HashMap<Integer, Integer> adjWeight; // An map containing the weight from this node to its adjacency nodes

	// constructor
	Node() {
		Id = 0;
		adj = new ArrayList<Integer>();
		adjWeight = new HashMap<Integer, Integer>(); // key = adjacency node, value = weight
	}

	// set/get functions
	public void SetId(Integer id) {
		this.Id = id;
	}

	public Integer GetId() {
		return this.Id;
	}

	public void setAdj(Integer id) {
		adj.add(id);
	}

	public List<Integer> GetAdj() {
		return adj;
	}

	public void setWei(Integer adj, Integer wei) {
		adjWeight.put(adj, wei);
	}

	public HashMap<Integer, Integer> GetWei() {
		return adjWeight;
	}
}
