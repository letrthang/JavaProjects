package com.letrungthang.blogspot.model;

public class Cell {

	public String row; // char eg. A, B...
	public String column; // number eg. 1, 20...
	public String expression; // content of the cell

	public Cell(String RowVal, String ColumnVal, String ExpressionVal) {
		row = RowVal;
		column = ColumnVal;
		expression = ExpressionVal;
	}
}
