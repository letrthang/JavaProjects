package model;

import java.util.HashSet;
import java.util.Set;


public class Worker {

	private int id;
	private Set<Day> workDays;
	private Set<Day> offDays;
	private String name;
	
	// constructor
	public Worker(int ID, String nameWker) {
		id = ID;
		name = nameWker;
		workDays = new HashSet<Day>(); // all working days of a worker
		offDays = new HashSet<Day>(); // all off days of a worker
	}

	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the workDays
	 */
	public Set<Day> getWorkDays() {
		return workDays;
	}

	/**
	 * @param workDays
	 *            the workDays to set
	 */
	public void setWorkDays(Day workDay) {
		this.workDays.add(workDay);
	}

	/**
	 * @return the offDays
	 */
	public Set<Day> getOffDays() {
		return offDays;
	}

	/**
	 * @param offDays
	 *            the offDays to set
	 */
	public void setOffDays(Day offDay) {
		this.offDays.add(offDay);
	}

	public String offDaysToString() {

		String offDaysStr = "";

		for (Day day : offDays) {
			offDaysStr += day.getDay().toString() + ", ";
		}
		return offDaysStr;
	}

	public String workDaysToString() {

		String workDaysStr = "";

		for (Day day : workDays) {
			workDaysStr += day.getDay().toString() + ", "; 
		}
		return workDaysStr;
	}

	public boolean isAssignedWorkDay() {

		boolean ret;
		if (workDays.size() == 5)
			ret = true;
		else
			ret = false;

		return ret;
	}

	public boolean isAssignedOffDay() {

		boolean ret;
		if (offDays.size() == 2)
			ret = true;
		else
			ret = false;

		return ret;
	}

}
