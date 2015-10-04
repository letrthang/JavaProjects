package controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
	 * To assign off days and work days for all workers
	 */
	public void assignScheduleToWorkers() {
		int workerIndex = 0;
		
		// 1st: set work days for workers randomly
		for (Day day : daysLst) {
			// weekday needs minimum 2 workers
			if ((day.getDay() != eDays.Sat) && (day.getDay() != eDays.Sun)) {
				while (day.getWorkers().size() < 2) {
					workerIndex = genRandomNumber(0, workersLst.size() -1); //take randomly index of a worker
					Worker worker = workersLst.get(workerIndex);

					if (worker.isAssignedWorkDay() == false) {
						day.setWorkers(worker);
						// update work day for worker
						workersLst.get(workerIndex).setWorkDays(day);
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
						workersLst.get(workerIndex).setWorkDays(day);
					}
				}
			}
		}

		// set work-days for workers not complete assignment at 1st step
		for (Worker wrker : workersLst) {
			int i = 6;
			while (wrker.isAssignedWorkDay() == false) {
				wrker.setWorkDays(daysLst.get(i));
				// update worker list of respective day
				daysLst.get(i).setWorkers(wrker);
				i--;
			}
		}

		// set off-days for workers not complete assignment at 1st step
		for (Worker wrker : workersLst) {
			int j = 0;
			while (wrker.isAssignedOffDay() == false) {
				if (!wrker.getWorkDays().contains(daysLst.get(j))) {
					wrker.setOffDays(daysLst.get(j));
				}
				j++;
			}
		}

	}

	/**
	 * return a random number in range min:max
	 * 
	 * @param min
	 * @param max
	 * @return a random number
	 */
	public int genRandomNumber(int min, int max) {
		int rad = 0;

		rad = ThreadLocalRandom.current().nextInt(min, max + 1);

		return rad;
	}

}
