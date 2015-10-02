package model;

import java.util.HashSet;
import java.util.Set;

import common.eDays;

public class Day {

	private int dayId;
	private eDays day;
	private Set<Worker> workers; // all workers associate with the day

	public Day(int id, eDays wd) {
		dayId = id;
		day = wd;
		workers = new HashSet<Worker>();
	}

	public int getId() {
		return dayId;
	}

	public void setId(int id) {
		this.dayId = id;
	}

	public eDays getDay() {
		return day;
	}

	public void setDay(eDays day) {
		this.day = day;
	}

	public Set<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(Worker worker) {
		this.workers.add(worker);
	}

	public String workersToString() {

		String workerStr = "";

		for (Worker wker : workers) {
			workerStr += wker.getName() + ", ";
		}
		return workerStr;
	}

}
