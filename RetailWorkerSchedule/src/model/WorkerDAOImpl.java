package model;

import java.util.List;

public class WorkerDAOImpl implements WorkerDAO {

	public WorkerDAOImpl() {

	}

	/*
	 * we can implement to read worker list from a text file or DB here.
	 * 
	 * @see model.WorkerDAO#readWorkerListFromFile(java.lang.String)
	 */
	public List<Worker> readWorkerListFromFile(String filePath) {
		return null;
	}
}
