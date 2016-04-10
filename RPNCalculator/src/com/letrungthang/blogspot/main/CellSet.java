package com.letrungthang.blogspot.main;

import java.util.HashSet;
import java.util.Set;

/*
 * This class is a wrapper of standard Set for supporting Cell class
 */
public class CellSet {
	private Set<Cell> cellSet;

	CellSet() {
		cellSet = new HashSet<Cell>();
	}

	public boolean add(Cell cell) {

		for (Cell ce : cellSet) {
			if ((ce.row.equals(cell.row)) && (ce.column.equals(cell.column))) {
				return false;
			}
		}

		return cellSet.add(cell);
	}

	public boolean remove(Cell cell) {
		return cellSet.remove(cell);
	}

	public boolean contains(Cell cell) {
		for (Cell ce : cellSet) {
			if ((ce.row.equals(cell.row)) && (ce.column.equals(cell.column))) {
				return true;
			}
		}

		return false;
	}
}
