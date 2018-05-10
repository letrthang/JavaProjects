import java.util.ArrayList;
import java.util.List;

public class Node {
	// id only for debug/test purpose
	private int id;
	// children of this node
	private List<Node> Children;
	// right node of this node
	private Node Right;
	// we need link this node to its parent node for tracking back.
	private Node parent;
	// The flag to mark whether this node was completed set right or not.
	// A Node is in completed status if all its children (except the most right
	// child node) were set right.
	private boolean isCompleted;

	public Node(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the children
	 */
	public List<Node> getChildren() {
		return Children;
	}

	/**
	 * @param children
	 *            the children to add
	 */
	public void addChildren(List<Node> children) {
		if (Children == null) {
			Children = new ArrayList<Node>();
		}

		Children.addAll(children);
	}

	/**
	 * @return the right
	 */
	public Node getRight() {
		return Right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Node right) {
		Right = right;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted
	 *            the isCompleted to set
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}
