import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * To see testing tree visualization in /docs/test_tree_2.PNG
 *
 */
public class TreeTest2 {

	Node node0;
	Node node1;
	Node node2;
	Node node3;
	Node node4;
	Node node5;
	Node node6;
	Node node7;
	Node node8;
	Node node9;
	Node node10;
	Node node11;
	Node node12;
	Node node13;
	Tree tree;

	@Before
	public void beforeEachTest() {
		// create nodes with different id
		node0 = new Node(0);
		node1 = new Node(1);
		node2 = new Node(2);
		node3 = new Node(3);
		node4 = new Node(4);
		node5 = new Node(5);
		node6 = new Node(6);
		node7 = new Node(7);
		node8 = new Node(8);
		node9 = new Node(9);
		node10 = new Node(10);
		node11 = new Node(11);
		node12 = new Node(12);
		node13 = new Node(13);
		// set children
		node0.addChildren(Arrays.asList(node1, node2, node3));
		node1.addChildren(Arrays.asList(node4, node5, node6, node7));
		node3.addChildren(Arrays.asList(node8, node9));
		node9.addChildren(Arrays.asList(node10, node11));
		node11.addChildren(Arrays.asList(node12));
		node12.addChildren(Arrays.asList(node13));
		// set parent
		node1.setParent(node0);
		node2.setParent(node0);
		node3.setParent(node0);
		node4.setParent(node1);
		node5.setParent(node1);
		node6.setParent(node1);
		node7.setParent(node1);
		node8.setParent(node3);
		node9.setParent(node3);
		node10.setParent(node9);
		node11.setParent(node9);
		node12.setParent(node11);
		node13.setParent(node12);
		// create tree.
		tree = new Tree();
		// set root node for tree is node0
		tree.setRootNode(node0);
	}

	@Test
	public void printTree() {
		// just print tree for debug
		tree.printTree();
	}

	@Test
	public void test1() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node1));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(node2, tree.getRightNode(node1));
	}

	@Test
	public void test2() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node6));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(node7, tree.getRightNode(node6));
	}

	@Test
	public void test3() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node0));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(null, tree.getRightNode(node0));
	}

	@Test
	public void test4() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node8));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(node9, tree.getRightNode(node8));
	}

	@Test
	public void test5() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node10));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(node11, tree.getRightNode(node10));
	}

	@Test
	public void test6() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node7));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(node8, tree.getRightNode(node7));
	}

	@Test
	public void test7() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node12));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(null, tree.getRightNode(node12));
	}

	@Test
	public void test8() {
		// before set right
		Assert.assertEquals(null, tree.getRightNode(node13));
		// set right node for tree
		tree.setRightNodeForTree();
		// after set right
		Assert.assertEquals(null, tree.getRightNode(node13));
	}
}
