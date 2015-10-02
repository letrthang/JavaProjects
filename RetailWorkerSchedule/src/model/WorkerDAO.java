package model;

import java.util.List;

public interface WorkerDAO {

	/*
	 * This method to read worker list from a formatted text file (or from DB if needed).
	 */ 
	public List<Worker> readWorkerListFromFile(String filePath);
	/*
	 * This method to save worker list to a formatted text file (or from DB if needed).
	 * User can add new worker from a GUI (View) and save new workers via this method.
	 * However, this program doesn't have a GUI, so no need to implement this method. Just design idea :))
	 */ 
	public boolean writeWorkerListToFile(List<Worker> workers);
}
