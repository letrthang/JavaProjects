package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WorkerDAOImpl implements WorkerDAO {

	public WorkerDAOImpl() {

	}

	/**
	 * This method to read worker list from a formatted text file (or from DB if needed) here.
	 * 
	 * @return list of Workers
	 */
	public List<Worker> readWorkerListFromFile(String filePath) {
		
			String inputWorkers = "";
			String workerName = "";

			List<String> workerNameList = new ArrayList<String>();
			List<Worker> workersLst = new ArrayList<Worker>();
			
			try {
				inputWorkers = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			inputWorkers.toUpperCase();
			inputWorkers.trim();
			System.out.println("Read input workers: " + inputWorkers);

			for (int i = 0; i < inputWorkers.length(); i++) {
				if (inputWorkers.charAt(i) != ",".charAt(0)) {
					workerName += inputWorkers.substring(i, i + 1);
				} else {
					workerName = workerName.trim();
					if (workerName.length() == 0) {
						System.out.println("Warning: wrong input format");
					} else {
						workerNameList.add(workerName);
					}
					workerName = "";
				}
				// process the last edge of the graph
				if (i == inputWorkers.length() - 1) {
					workerName = workerName.trim();
					if (workerName.length() == 0) {
						System.out.println("Warning: wrong input format");
					} else {
						workerNameList.add(workerName);
					}
				}
			}

			// convert worker name to Worker object
			for (int i = 0; i < workerNameList.size() ; i++) {
				Worker wker = new Worker(i, workerNameList.get(i));
				workersLst.add(wker);
			}

			return workersLst;

		
	}
	
	/*
	 * This method to save worker list to a formatted text file (or from DB if needed).
	 * User can add new worker from a GUI (View) and save new workers via this method.
	 * However, this program doesn't have a GUI, so no need to implement this method. Just design idea :))
	 */ 
	public boolean writeWorkerListToFile(List<Worker> workers) {
		return true;
	}
}
