package view;

import java.util.List;

import model.Day;
import model.Worker;

public class ScheduleView {

	private List<Worker> workersLst;
	private List<Day> daysLst;

	/**
	 * print
	 */

	public ScheduleView(List<Worker> workers, List<Day> days) {
		workersLst = workers;
		daysLst = days;
	}

	public void showWorkerSchedule() {

		String workDays = "";
		String offDays = "";

		System.out.println("\n         ***Worker Schedule***");
		
		for (int i = 0; i < workersLst.size(); i++) {
			workDays = workersLst.get(i).workDaysToString();
			offDays = workersLst.get(i).offDaysToString();

			System.out.println("============ Name: " + workersLst.get(i).getName() + " ============");
			System.out.println("      work days: " + workDays);
			System.out.println("      off days: " + offDays);
		}
	}

	/**
	 * 
	 */
	public void showRetailCalendar() {
		String workersStr = "";

		System.out.println("\n       ***Retail Schedule***");
		
		for (int i = 0; i < daysLst.size(); i++) {
			workersStr = daysLst.get(i).workersToString();
			System.out.println(daysLst.get(i).getDay().toString() + ": " + workersStr);
		}

	}

}
