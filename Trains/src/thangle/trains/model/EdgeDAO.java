/**
 * 
 */
package thangle.trains.model;

import java.util.List;

/**
 * @author Thang Le
 *
 */
public interface EdgeDAO {
	public List<Edge> readEdge(String inputFile) ;
	public  int readGraphSize(String inputFile);
}
