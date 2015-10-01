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

		List<Worker> workersLst = new ArrayList<>();
		List<String> workersNameLst;
		List<Day> daysLst = new ArrayList<Day>();

		WorkerSchedule workerSchedule;
		ScheduleView scheduleView;

		/*
		 *  Initialize the resource file path
		 */
		String filePath = "resource/Workers_1.txt";

		WorkerDAO workerDAO = new WorkerDAOImpl();
		workersNameLst = workerDAO.readWorkerListFromFile(filePath);
		// 1. initialize workersLst from input text file
		for (int i = 0; i < workersNameLst.size() ; i++) {
			Worker wker = new Worker(i, workersNameLst.get(i));
			workersLst.add(wker);
		}

		// 2. initialize daysLst. put all weekday to this list
		for (int i = 0; i <= 6; i++) {
			daysLst.add(new Day(i, Util.numToDay(i)));
		}

		// 3. initialize controller and view
		workerSchedule = new WorkerSchedule(workersLst, daysLst);
		scheduleView = new ScheduleView(workersLst, daysLst);

		workerSchedule.assignScheduleToWorkers();
		scheduleView.showWorkerSchedule();
		scheduleView.showRetailCalendar();
	}

}
