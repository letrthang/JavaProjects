import java.util.Arrays;
import java.util.List;

public class Tree {

	private Node rootNode;

	/**
	 * @return the rootNode
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * @param rootNode
	 *            the rootNode to set
	 */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * Wrapper to set right node
	 */
	public void setRightNodeForTree() {
		setRightNodeForTree(this.rootNode);
	}

	/**
	 * Wrapper to print tree
	 */
	public void printTree() {
		printTree(this.rootNode);
	}

	/**
	 * Get right node of a node
	 * 
	 * @param node
	 * @return
	 */
	public Node getRightNode(Node node) {
		Node rightNode = null;
		rightNode = node.getRight();

		System.out.println(
				"Right node of: " + node.getId() + " is -> " + ((rightNode == null) ? "NULL" : rightNode.getId()));

		return rightNode;
	}

	/**
	 * Set right node for tree without recursive call/ queue or stack.
	 * 
	 * This consists 2 steps: set right for all nodes having same parent node and
	 * set right for all nodes having no same parent node.
	 * 
	 * For nodes have same parent, the idea is similar with Depth-first search
	 * (DFS). We start from root and moving on vertical, set right for its children
	 * and then move to any node (not completed status) of its children. repeat same
	 * thing until the leaf. When we reach the leaf, we move back to the parent and
	 * repeat set right again until all node were set right.
	 * 
	 * For nodes have not same parent, we move horizontal, the idea is similar
	 * Breadth-first search (BFS).
	 * 
	 * @param rootNode
	 */
	private void setRightNodeForTree(Node rootNode) {
		Node mostLeftNode = rootNode;
		Node currentNode = rootNode;
		Node nextCurrentNode = null;
		boolean isAllChildrenCompleted = true;

		// 1. set right for children having same parent.
		while (true) {
			List<Node> children = currentNode.getChildren();

			if (children == null || children.size() == 1) {
				// a. mark current node as completed.
				currentNode.setCompleted(true);
				// b. move back to its parent.
				currentNode = currentNode.getParent();
			} else {
				// a. set right node for children list of the current node.
				setRightNodeForSameParent(children);
				// b. update current node to new node which is not completed status.
				for (Node child : children) {
					if (child.isCompleted() == false) {
						currentNode = child;
						isAllChildrenCompleted = false;
						break;
					} else {
						isAllChildrenCompleted = true;
					}
				}
				// c. if all children were completed to set right.
				if (isAllChildrenCompleted == true) {
					// d. mark current node as completed.
					currentNode.setCompleted(true);
					// e. move back to its parent
					currentNode = currentNode.getParent();
				}
			}

			// f. We already traversed whole tree when root node is completed status.
			if (rootNode.isCompleted() == true) {
				break;
			}
		}

		// 2. set right for children having no same parent.
		// reset current node to root.
		mostLeftNode = rootNode;
		currentNode = mostLeftNode;
		nextCurrentNode = currentNode.getRight();
		while (true) {
			// a. we start from most left node of tree.
			currentNode = mostLeftNode;
			for (;;) {
				Node fromChildNode = null, toChildNode = null;

				if (currentNode != null && currentNode.getChildren() != null) {
					fromChildNode = currentNode.getChildren().get(currentNode.getChildren().size() - 1);
				}

				if (nextCurrentNode != null && nextCurrentNode.getChildren() != null) {
					toChildNode = nextCurrentNode.getChildren().get(0);
				}

				if (fromChildNode != null && toChildNode != null) {
					// b. set right
					fromChildNode.setRight(toChildNode);

					// c. current node jumps to its next node.
					currentNode = nextCurrentNode;
					nextCurrentNode = currentNode.getRight();
					continue;
				}
				// d. move current node on horizontal direction
				while (currentNode != null && currentNode.getChildren() == null) {
					currentNode = currentNode.getRight();
				}

				if (currentNode != null) {
					nextCurrentNode = currentNode.getRight();
				}
				// e. move next of current node on horizontal direction
				while (nextCurrentNode != null && nextCurrentNode.getChildren() == null) {
					nextCurrentNode = nextCurrentNode.getRight();
				}

				if (nextCurrentNode == null) {
					break;
				}
			}

			// f. now, to move the most left node on vertical direction to next level
			for (;;) {

				if (mostLeftNode != null && mostLeftNode.getChildren() != null) {
					mostLeftNode = mostLeftNode.getChildren().get(0);
					break;
				} else {
					mostLeftNode = mostLeftNode.getRight();
				}

				if (mostLeftNode == null) {
					break;
				}
			}

			// g. completed set right all children that are not same parent.
			if (mostLeftNode == null) {
				break;
			}

		}
	}

	/**
	 * set right node for given list of children of a node.
	 * 
	 * @param nodes
	 */
	private void setRightNodeForSameParent(List<Node> nodes) {
		for (int i = 0; i < nodes.size() - 1; i++) {
			nodes.get(i).setRight(nodes.get(i + 1));
		}
	}

	/**
	 * print all nodes of tree.
	 * 
	 * Just for debug so not to consider performance, so using recursive call here
	 * for simple coding.
	 * 
	 * @param rootNode
	 */
	private void printTree(Node rootNode) {
		Node currentNode = rootNode;
		System.out.println(currentNode.getId());

		List<Node> children = currentNode.getChildren();
		if (children == null || children.size() == 0) {
			return;
		} else {
			for (Node child : children) {
				// recursive call
				printTree(child);
			}
		}
	}

	// =============== Testing ============
	// More detail, check on unit test: TreeTest.java.
	public static void main(String[] args) {
		// create nodes with different id
		Node node0 = new Node(0);
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		Node node8 = new Node(8);
		Node node9 = new Node(9);
		Node node10 = new Node(10);
		Node node11 = new Node(11);
		Node node12 = new Node(12);
		// set children
		node0.addChildren(Arrays.asList(node1, node2, node3));
		node1.addChildren(Arrays.asList(node4, node5, node6));
		node2.addChildren(Arrays.asList(node7, node8));
		node3.addChildren(Arrays.asList(node9));
		node4.addChildren(Arrays.asList(node10, node11));
		node5.addChildren(Arrays.asList(node12));
		// set parent
		node1.setParent(node0);
		node2.setParent(node0);
		node3.setParent(node0);
		node4.setParent(node1);
		node5.setParent(node1);
		node6.setParent(node1);
		node7.setParent(node2);
		node8.setParent(node2);
		node9.setParent(node3);
		node10.setParent(node4);
		node11.setParent(node4);
		node12.setParent(node5);

		// create tree.
		Tree tree = new Tree();
		// set root node is node0
		tree.setRootNode(node0);
		// print to debug
		tree.printTree();
		// set right node
		tree.setRightNodeForTree();
		// get right node
		tree.getRightNode(node2);
		tree.getRightNode(node5);
		tree.getRightNode(node6);
		tree.getRightNode(node8);
		tree.getRightNode(node10);
		tree.getRightNode(node11);
		tree.getRightNode(node12);
	}

	// test tree with 2 nodes.
	public static void main1(String[] args) {
		// create nodes with different id
		Node node0 = new Node(0);
		Node node1 = new Node(1);
		// set children
		node0.addChildren(Arrays.asList(node1));
		// set parent
		node1.setParent(node0);

		// create tree.
		Tree tree = new Tree();
		// set root node is node0
		tree.setRootNode(node0);
		// print to debug
		tree.printTree();
		// set right node
		tree.setRightNodeForTree();
		// get right node
		tree.getRightNode(node1);
	}

}
