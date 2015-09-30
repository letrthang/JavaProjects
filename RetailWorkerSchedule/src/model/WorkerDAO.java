package model;

import java.util.List;

public interface WorkerDAO {

	public List<Worker> readWorkerListFromFile(String filePath);
}
