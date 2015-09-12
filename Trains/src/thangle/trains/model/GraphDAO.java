/**
 * 
 */
package thangle.trains.model;

import java.util.List;

/**
 * @author Thang Le
 *
 */
public interface GraphDAO {

	public void addEdge(Integer v, Integer a, Integer wei);
	public void addEdgeList(List<Edge> edgeList);
}
