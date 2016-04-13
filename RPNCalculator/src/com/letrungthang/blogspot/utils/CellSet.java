package com.letrungthang.blogspot.utils;

import java.util.HashSet;
import java.util.Set;

import com.letrungthang.blogspot.model.Cell;

/*
 * This class is a wrapper of standard Set for supporting Cell class
 */
public class CellSet {
	private Set<Cell> cellSet;

	public CellSet() {
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
	
	public void clear(){
		if(cellSet != null){
			cellSet.clear();
		}
	}
}
