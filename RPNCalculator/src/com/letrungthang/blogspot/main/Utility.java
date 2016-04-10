/**
 * 
 */
package com.letrungthang.blogspot.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ThangLe
 *
 */
public class Utility {

	/*
	 * verify whether a cell having at least one operand reference to an other
	 * cell
	 */
	public static boolean isReferencedCell(String expression) {
		boolean ret = false;

		// split string at whitespace
		String[] exprSplit = expression.split("\\s+");
		for (int i = 0; i < exprSplit.length; i++) {
			String character = exprSplit[i];
			if (Character.isLetter(character.charAt(0))) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	/*
	 * To build a Cell. 
	 * RowVal: character from A->Z
	 * ColumnVal: number from 1->n
	 */
	public static Cell buildCell(String RowVal, String ColumnVal) {

		Integer rowIndex = 0; // 
		Integer columnIndex = 0;
		String expr = "";
		
		rowIndex = RowVal.toUpperCase().charAt(0) - 'A';
		columnIndex = Integer.parseInt(ColumnVal) - 1;
		// To get expression of cell at position (row, cloumn)
		expr = Global.gCellMatrix.get(rowIndex).get(columnIndex);
		
		return new Cell(RowVal, ColumnVal, expr);
	}

	/*
	 * read csv file and convert cell position to a 2D matrix
	 */
	public static void CSVReader(List<List<String>> CellMatrix) {

		String csvFile = "resource/input_data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		int rowLen = 0, columnLen = 0;
		int row = 0, column = 0;

		try {

			br = new BufferedReader(new FileReader(csvFile));
			// read 1st line of csv file to know number of rows and columns
			line = br.readLine();
			if (line != null) {
				String firstLine[] = line.split(cvsSplitBy);
				String secondCellOfFirstLine = firstLine[1];
				String RowColumns[] = secondCellOfFirstLine.split("\\s+");
				rowLen = Integer.parseInt(RowColumns[0]);
				columnLen = Integer.parseInt(RowColumns[1]);
			}
			// init rows of matrix
			for (int i = 0; i < rowLen; i++) {
				CellMatrix.add(new ArrayList<String>());
			}

			// read next lines and add to 2D matrix
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] nextLine = line.split(cvsSplitBy);
				for (int i = 0; i < columnLen; i++) {
					CellMatrix.get(row).add(nextLine[i + 1]);
				}
				row++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Read CSV Done!");
	}

	// for test
	public static void main_test(String[] args) {
		String test = "81 Z5 +";
		boolean res = false;
		// res = IsReferencedCell(test);
		// System.out.println("res =" + res);
		// CSVReader();
	}
}
