/**
 * 
 */
package thangle.trains.model;

/**
 * @author Thang Le
 * 
 * This class stores a Edge of graph (eg. AB5, BC6...)
 */
public class Edge {
	Integer startNode = 0; // start node of edge
	Integer endNode = 0; // end node of edge
	Integer weight = 0; // weight of edge

	// set/get functions
	public void setStartNode(Integer startNode) {
		this.startNode = startNode;
	}

	public void setEndNode(Integer endNode) {
		this.endNode = endNode;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getStartNode() {
		return startNode;
	}

	public Integer getEndNode() {
		return endNode;
	}

	public Integer getWeight() {
		return weight;
	}
}
