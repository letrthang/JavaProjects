package algorithm;

//https://www.youtube.com/watch?v=wcIRPqTR3Kc
public class BinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree bt = new BinarySearchTree();
		bt.add(6);
		bt.add(4);
		bt.add(8);
		bt.add(3);
		bt.add(5);
		System.out.println("contain: " + 1 + " -->" + bt.containsNode(1));
		System.out.println("contain: " + 5 + " -->" + bt.containsNode(5));
	}

	/**
	 * Node of this binary search tree.
	 *
	 */
	class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
			right = null;
			left = null;
		}
	}

	Node root; // root node

	private Node addRecursive(Node current, int value) {
		if (current == null) {
			return new Node(value);
		}

		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value) {
			current.right = addRecursive(current.right, value);
		} else {
			// value already exists
			return current;
		}

		return current; // it is root node after recursive completed.
	}

	public void add(int value) {
		root = addRecursive(root, value);
	}

	private boolean containsNodeRecursive(Node current, int value) {
		if (current == null) {
			return false;
		}
		if (value == current.value) {
			return true;
		}
		return value < current.value ? containsNodeRecursive(current.left, value)
				: containsNodeRecursive(current.right, value);
	}

	public boolean containsNode(int value) {
		return containsNodeRecursive(root, value);
	}

}
