package controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import common.Util;
import common.eDays;
import model.Day;
import model.Worker;

public class WorkerSchedule {

	public List<Worker> workersLst;
	public List<Day> daysLst;

	public WorkerSchedule(List<Worker> wker, List<Day> days) {
		this.workersLst = wker;
		this.daysLst = days;
	}

	/**
	 * assign off days and work days for all workers
	 */
	public void assignScheduleToWorkers() {
		int workerIndex = 0;
		
		// 1st: set work days for workers randomly
		for (Day day : daysLst) {
			// weekday needs minimum 2 workers
			if ((day.getDay() != eDays.Sat) && (day.getDay() != eDays.Sun)) {
				while (day.getWorkers().size() < 2) {
					workerIndex = genRandomNumber(0, workersLst.size() -1); // take randomly index of a worker
					Worker worker = workersLst.get(workerIndex);

					if (worker.isAssignedWorkDay() == false) {
						day.setWorkers(worker);
						// update work day for worker
						workersLst.get(workerIndex).setWorkDays(day.getDay());
					}

				}
				// weekend needs minimum 3 workers
			} else {
				while (day.getWorkers().size() < 3) {
					workerIndex = genRandomNumber(0, workersLst.size() -1); // take randomly index of a worker
					Worker worker = workersLst.get(workerIndex);

					if (worker.isAssignedWorkDay() == false) {
						day.setWorkers(worker);
						// update work day for worker
						workersLst.get(workerIndex).setWorkDays(day.getDay());
					}
				}
			}
		}

		// set work-days for workers not complete assignment at 1st step
		for (Worker wrker : workersLst) {
			int i = 0;
			while (wrker.isAssignedWorkDay() == false) {
				wrker.setWorkDays(Util.numToDay(i));
				// update worker list of respective day
				daysLst.get(i).setWorkers(wrker);
				i++;
			}
		}

		// set off-days for workers not complete assignment at 1st step
		for (Worker wrker : workersLst) {
			int j = 0;
			while (wrker.isAssignedOffDay() == false) {
				if (!wrker.getWorkDays().contains(Util.numToDay(j))) {
					wrker.setOffDays(Util.numToDay(j));
				}
				j++;
			}
		}

	}

	/**
	 * return random day
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public int genRandomNumber(int min, int max) {
		int rad = 0;

		rad = ThreadLocalRandom.current().nextInt(min, max + 1);

		return rad;
	}

}
