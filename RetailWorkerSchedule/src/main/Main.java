package main;

/**
 * @author Thang Le (letrthang@gmail.com)
 */
import java.util.ArrayList;
import java.util.List;

import controller.WorkerSchedule;
import model.Day;
import model.Worker;
import model.WorkerDAO;
import model.WorkerDAOImpl;
import view.ScheduleView;
import common.Util;

public class Main {

	public static void main(String[] args) {

		List<Worker> workersLst ;
		List<Day> daysLst = new ArrayList<Day>();

		WorkerSchedule workerSchedule;
		ScheduleView scheduleView;

		// 1. Initialize the resource file path
		String filePath = "resource/Workers_1.txt";

		// 2. initialize workersLst by reading workers from input text file
		WorkerDAO workerDAO = new WorkerDAOImpl();
		workersLst = workerDAO.readWorkerListFromFile(filePath);
		
		// 3. initialize list of weekdays, from Mon to Sun. Put all weekdays to this list
		for (int i = 0; i <= 6; i++) {
			daysLst.add(new Day(i, Util.numToDay(i)));
		}

		// 4. initialize controller and view
		workerSchedule = new WorkerSchedule(workersLst, daysLst); //controller
		scheduleView = new ScheduleView(workersLst, daysLst); // view

		// 5. execute and display results
		workerSchedule.assignScheduleToWorkers(); // do a calculation
		scheduleView.showWorkerSchedule(); // display schedule of each worker
		scheduleView.showRetailCalendar();  // display calendar of retail
	}

}
