package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WorkerDAOImpl implements WorkerDAO {

	public WorkerDAOImpl() {

	}

	/*
	 * we can implement to read worker list from a text file or DB here.
	 * 
	 * @see model.WorkerDAO#readWorkerListFromFile(java.lang.String)
	 */
	public List<String> readWorkerListFromFile(String filePath) {
		
			String inputWorkers = "";
			String workerName = "";

			List<String> workerList = new ArrayList<String>();
			try {
				inputWorkers = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			inputWorkers.toUpperCase();
			inputWorkers.trim();
			System.out.println("Read input worker: " + inputWorkers);

			for (int i = 0; i < inputWorkers.length(); i++) {
				if (inputWorkers.charAt(i) != ",".charAt(0)) {
					workerName += inputWorkers.substring(i, i + 1);
				} else {
					workerName = workerName.trim();
					if (workerName.length() == 0) {
						System.out.println("Warning: wrong input format");
					} else {
						workerList.add(workerName);
					}
					workerName = "";
				}
				// process the last edge of the graph
				if (i == inputWorkers.length() - 1) {
					workerName = workerName.trim();
					if (workerName.length() == 0) {
						System.out.println("Warning: wrong input format");
					} else {
						workerList.add(workerName);
					}
				}
			}

			return workerList;

		
	}
}
