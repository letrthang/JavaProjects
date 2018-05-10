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
	 * Wrapper to set right node.
	 * 
	 * Both methods "setRightNodeForTree" and "setRightNodeForTree2" are same
	 * functional, just different way of implementation.
	 */
	public void setRightNodeForTree() {
		setRightNodeForTree2(this.rootNode);
		// setRightNodeForTree(this.rootNode);
	}

	/**
	 * Wrapper to print tree.
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
	 * The idea is similar Breadth-first search (BFS). We traverse tree from most
	 * left node of the tree and moving horizontal to completed set right all
	 * children node (children with same and not same parent) at same level before
	 * shifting down to next level until completed whole the tree.
	 * 
	 * @param rootNode
	 */
	private void setRightNodeForTree2(Node rootNode) {
		Node mostLeftNode = rootNode;
		Node currentNode = rootNode;
		Node nextCurrentNode = null;

		// 1. set current node and most left node to root.
		mostLeftNode = rootNode;
		currentNode = mostLeftNode;
		nextCurrentNode = currentNode.getRight();
		while (true) {
			// 2. we start from most left node of tree.
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
					// 3. set right for 2 nodes same level but having different parent.
					fromChildNode.setRight(toChildNode);
					// 4. then current node jumps to its next node.
					currentNode = nextCurrentNode;
					nextCurrentNode = currentNode.getRight();
					continue;
				}

				// 5. keep moving current node on horizontal direction
				while (currentNode != null && currentNode.getChildren() == null) {
					// until we find a node having children
					currentNode = currentNode.getRight();
				}
				// 6. we set right all children of this current node except most right child.
				if (currentNode != null && currentNode.isCompleted() == false) {
					setRightNodeForChildren2(currentNode);
				}

				if (currentNode != null) {
					nextCurrentNode = currentNode.getRight();
				}
				// 7. keep moving next of current node on horizontal direction
				while (nextCurrentNode != null && nextCurrentNode.getChildren() == null) {
					// until we find a node having children
					nextCurrentNode = nextCurrentNode.getRight();
				}
				// 8. we set right all children of this next node except most right child.
				if (nextCurrentNode != null && nextCurrentNode.isCompleted() == false) {
					setRightNodeForChildren2(nextCurrentNode);
				}

				if (nextCurrentNode == null) {
					break;
				}
			}

			// 9. now, to move the most left node on vertical direction to next level
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

			// 10. completed set right all children that are not same parent.
			if (mostLeftNode == null) {
				break;
			}

		}
	}

	/**
	 * Set right node for given list of children of a node. The most right node will
	 * not be set right.
	 * 
	 * In this implementation, if all children of a node (except most right child
	 * node) are set right, then this node is in completed status. if a node only
	 * have one child or no child then we treat it as a completed status also. This
	 * definition is different with method "setRightNodeForChildren". It is depending
	 * on your implementation.
	 * 
	 * @param node:
	 *            node that its children to be set right.
	 */
	private void setRightNodeForChildren2(Node node) {
		if (node == null) {
			return;
		}

		List<Node> nodes = node.getChildren();

		if (nodes != null) {
			for (int i = 0; i < nodes.size() - 1; i++) {
				nodes.get(i).setRight(nodes.get(i + 1));
			}
		}
		// after all children (except most right child node) are set right, then this
		// node is marked as completed status.
		node.setCompleted(true);
	}

	/**
	 * Set right node for tree without recursive call/ queue or stack.
	 * 
	 * This consists 2 steps: set right for all nodes having same parent node and
	 * set right for all nodes having no same parent node.
	 * 
	 * For nodes have same parent, the idea is similar with Depth-first search
	 * (DFS). We start from root and moving on vertical, set right for its children
	 * and then move to any node (that is not completed status) of its children. To
	 * repeat same thing until the leaf. When we reach the leaf node, we move back
	 * to the parent and repeating set right again until all nodes having same
	 * parent are set right.
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

		/*
		 * step 1. set right for children having same parent.
		 */
		while (true) {
			List<Node> children = currentNode.getChildren();

			if (children == null || children.size() == 1) {
				// a. mark current node as completed.
				currentNode.setCompleted(true);
				// b. move back to its parent.
				currentNode = currentNode.getParent();
			} else {
				// a. set right node for children list of the current node.
				setRightNodeForChildren(children);
				// b. update current node to new node which is not completed status.
				for (Node child : children) {
					if (child.isCompleted() == false) {
						// move down current node to its child
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

		/*
		 * step 2. set right for children having no same parent.
		 */
		// reset current node and most left node to root.
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
	 * Set right node for given list of children of a node.
	 * 
	 * In this implementation, a node is in completed status if it has only one
	 * child OR no child OR all its children are in completed status.
	 * 
	 * @param nodes
	 */
	private void setRightNodeForChildren(List<Node> nodes) {
		if (nodes == null) {
			return;
		}

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
