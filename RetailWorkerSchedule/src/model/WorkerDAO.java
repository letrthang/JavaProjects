package model;

import java.util.List;

public interface WorkerDAO {

	public List<String> readWorkerListFromFile(String filePath);
}
