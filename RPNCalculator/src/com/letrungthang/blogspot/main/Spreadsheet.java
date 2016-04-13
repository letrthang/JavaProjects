package com.letrungthang.blogspot.main;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.letrungthang.blogspot.global.Global;
import com.letrungthang.blogspot.model.Cell;
import com.letrungthang.blogspot.utils.CellSet;
import com.letrungthang.blogspot.utils.Utility;

/**
 * @author Thang Le.
 * @version 10 April 2016
 *
 */
public class Spreadsheet {
	// keep tracking cells visited
	private CellSet vistedCells = new CellSet();
	// matrix of cells in input spreadsheet.
	private static List<List<String>> CellMatrix;

	Spreadsheet() {
		CellMatrix = Global.gCellMatrix;
	}

	/**
	 * To detect whether expression of a cell is cyclic dependencies or not.
	 * 
	 * @return true: cell is cyclic dependency. false: cell is no cyclic
	 *         dependency.
	 */
	public boolean detectCyclicDependency(Cell cell) {
		boolean ret = false;

		if (!Utility.isReferencedCell(cell.expression)) {
			// no harm even if the cell not exist on the Set
			vistedCells.remove(cell);
			// A non-reference cell is a cell having no reference to other cells
			// to get its value
			return false;
		} else {

			if (vistedCells.contains(cell)) {
				return true; // cyclic reference
			} else {
				// add the cell visited to a set for tracking back
				vistedCells.add(cell);

				// split the expression at white space
				String exprSplit[] = cell.expression.split("\\s+");
				for (int i = 0; i < exprSplit.length; i++) {

					String character = exprSplit[i];
					// find operand of expression that references to other cell
					if (Character.isLetter(character.charAt(0))) {
						Cell cellBld = Utility.buildCell(character.substring(0, 1), character.substring(1));
						// Recursive to visit all dependencies of the current
						// cell
						ret = detectCyclicDependency(cellBld);
						// detect cyclic dependency
						if (ret == true) {
							// stop tracking if there is a cell cyclic
							// dependency found
							vistedCells.clear();
							break;
						} else {
							// remove cell that current cell references to.
							vistedCells.remove(cellBld);
							// And remove itself also. Sorry, it is really nightmare
							// code, hope you understand :))
							vistedCells.remove(cell);
						}
					}
				}
			}

		}

		return ret;
	}

	/**
	 * This method tests to see whether the value of a String is a legal RPN
	 * mathematical operator or not.
	 * 
	 * @param next
	 *            is the value to be tested
	 * @return whether the next value is a mathematical operator or not
	 */
	public boolean nextIsOperator(String next) {
		return (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/"));
	}

	/**
	 * check if an operand is linking to an other cell
	 * 
	 */
	public boolean nextIsReferencedCell(String next) {
		if (Character.isLetter(next.charAt(0))) {
			return true;
		}

		return false;
	}

	/**
	 * Reverse Polish Notation algorithm
	 * 
	 * @param cell
	 */
	public double RPNAlgorithm(Cell cell) throws Exception {

		// scanner to manipulate input and stack to store double values
		String next;
		Stack<Double> stack = new Stack<Double>();
		Scanner scan = new Scanner(cell.expression.trim());

		// loop while there are tokens left in scan
		while (scan.hasNext()) {
			// retrieve the next token from the input
			next = scan.next();

			// see if token is mathematical operator
			if (nextIsOperator(next)) {
				// ensure there are enough numbers on stack
				if (stack.size() > 1) {
					if (next.equals("+")) {
						stack.push((Double) stack.pop() + (Double) stack.pop());
					} else if (next.equals("-")) {
						stack.push(-(Double) stack.pop() + (Double) stack.pop());
					} else if (next.equals("*")) {
						stack.push((Double) stack.pop() * (Double) stack.pop());
					} else if (next.equals("/")) {
						double first = stack.pop();
						double second = stack.pop();

						if (first == 0) {
							System.out.println("The RPN equation attempted to divide by zero.");
							throw new Exception();
						} else {
							stack.push(second / first);
						}
					}
				} else {
					System.out.println(
							"A mathematical operator occured before there were enough numerical values for it to evaluate.");
					throw new Exception();
				}
			} else {
				try {
					if (nextIsReferencedCell(next)) {
						Cell ce = Utility.buildCell(next.substring(0, 1), next.substring(1));
						// Recursive here to calculate operand that referencing
						// on other cell
						stack.push(RPNAlgorithm(ce));
					} else {
						// put into stack immediately if operand not linking to
						// any other cell
						stack.push(Double.parseDouble(next));
					}
				} catch (NumberFormatException c) {
					System.out.println("The string is not a valid RPN equation.");
					throw new Exception();
				}
			}
		}

		if (stack.size() > 1) {
			System.out.println(
					"There too many numbers and not enough mathematical operators with which to evaluate them.");
			throw new Exception();
		}

		return (Double) stack.pop();
	}

	/*
	 * entry function
	 */
	public static void main(String[] args) {

		int numRow = 0;
		int numCol = 0;
		String row = null;
		String column = null;
		boolean ret = true;

		// 1. Initialize Spreadsheet
		Spreadsheet spr = new Spreadsheet();
		// 2. read csv and convert cells to a 2D matrix
		Utility.CSVReader(CellMatrix);

		numRow = CellMatrix.size(); // get number of rows
		numCol = CellMatrix.get(0).size(); // get number of columns
		System.out.println("Number row: " + numRow + ", number column: " + numCol + "\n");

		// 3. Starting calculate RPN
		for (int i = 0; i < numRow; i++) {
			for (int j = 0; j < numCol; j++) {
				row = Character.toString((char) (i + 'A'));
				column = Integer.toString(j + 1);

				// 3.1. detect cyclic dependency first
				Cell ce = Utility.buildCell(row, column);
				ret = spr.detectCyclicDependency(ce);
				if (ret) {
					System.out.println("Cell " + row + column + " has cyclic dependencies");
				} else {

					// 3.2. Actual Calculate RPN
					try {
						double rpnRes = 0;
						rpnRes = spr.RPNAlgorithm(ce);
						String expr = Global.gCellMatrix.get(i).get(j);
						System.out.println(expr + "  ....................  " + String.format("%.5f", rpnRes));
					} catch (Exception e) {
						System.out.println("Hey... something went wrong...pls check the log!");
						e.printStackTrace();
					}

				}

			}
		}

	}

}
