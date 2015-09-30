package main;

import java.util.ArrayList;
import java.util.List;

import controller.WorkerSchedule;
import model.Day;
import model.Worker;
import view.ScheduleView;
import common.Util;

public class Main {

	public static void main(String[] args) {

		List<Worker> workersLst = new ArrayList<>();
		List<Day> daysLst = new ArrayList<Day>();

		WorkerSchedule workerSchedule;
		ScheduleView scheduleView;

		// initialize workersLst. for sample, retails has 4 workers 
		// but can increase number worker by increasing index of for loop below
		for (int i = 0; i < 4; i++) {
			Worker wker = new Worker(i, "worker " + i);
			workersLst.add(wker);
		}

		// initialize daysLst. put all weekday to this list
		for (int i = 0; i <= 6; i++) {
			daysLst.add(new Day(i, Util.numToDay(i)));
		}

		workerSchedule = new WorkerSchedule(workersLst, daysLst);
		scheduleView = new ScheduleView(workersLst, daysLst);
		
		workerSchedule.assignScheduleToWorkers();
		scheduleView.showWorkerSchedule();
		scheduleView.showRetailCalendar();
	}

}
