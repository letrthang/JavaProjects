package Splendid.Middleware.Form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Splendid.Middleware.Driver.LLRPmonitor;
import Splendid.Middleware.File.ReadWriteTextFile;
import Splendid.Middleware.ws.ReaderInfo;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CheckReader {

	private CsvReader reader;
	private CsvWriter writer;
	private Timer timer;
	private String outputFile = System.getProperty("user.dir") + "/ReaderInfo.csv";
	private String textFile = System.getProperty("user.dir") + "/endpoint.txt";
	private static List<ReaderStatus> readerStatusList = new ArrayList<ReaderStatus>();
	private static List<ReaderStatus> readerStatusListCurrent = new ArrayList<ReaderStatus>();
	private LLRPmonitor llRPmonitor;

	MainForm form;

	public CheckReader(MainForm form) {
		this.form = form;
	}

	public static List<ReaderStatus> getReaderStatusListCurrent() {

		return new ArrayList<ReaderStatus>(readerStatusListCurrent);
	}

	/*
	 * save reader to file
	 */
	public void AddReader(String location, String Ip) {

		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Controller");
				csvOutput.write("IPReader");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			// write out a few records
			csvOutput.write(location);
			csvOutput.write(Ip);
			csvOutput.endRecord();

			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ReaderStatus readerStatus = new ReaderStatus();
		readerStatus.controllerName = location.trim();
		readerStatus.ip = Ip.trim();
		readerStatus.status = false;

		// add to list
		readerStatusList.add(readerStatus);
		updateReaderTable(readerStatusList);
	}

	/*
	 * load readers from csv file and start check status
	 */
	public void CheckReaderStatus() {
		// load file

		readerStatusList.clear();
		try {
			Thread.sleep(500);

			CsvReader csvInput = new CsvReader(outputFile);
			csvInput.readHeaders();
			while (csvInput.readRecord())
			{
				ReaderStatus readerStatus = new ReaderStatus();
				readerStatus.controllerName = csvInput.get("Controller").trim();
				readerStatus.ip = csvInput.get("IPReader").trim();
				readerStatus.status = false;

				// add to list
				readerStatusList.add(readerStatus);
			}
			csvInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		readerStatusListCurrent.clear();// init current value
		
		// start check reader status
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new CheckReaderStatus(), 1000, 10000); // 10s
		}else {
			timer.cancel();
			timer = new Timer();
			timer.schedule(new CheckReaderStatus(), 1000, 10000); // 10s
		}
		// check file
		// boolean txtFileExists = new File(textFile).exists();
		String Ip = "";
		File txtFile = new File(textFile);

		Ip = form.getEndPoint();
		try {
			if (!txtFile.exists()) {
				txtFile.createNewFile();
			}

			if (Ip.equals("")) {
				Ip = ReadWriteTextFile.readTxtFile(txtFile);
			} else {
				ReadWriteTextFile.writeTxtFile(txtFile, Ip);
			}
		} catch (Exception e) {
			Ip = "localhost:8080";
		}
		form.setEndPoint(Ip);

		boolean res = false;
		res = ReaderInfo.readerServices(Ip); // public services

		if (res) {
			form.setTextOnOff("ON");
		} else {
			form.setTextOnOff("OFF");
		}
	}

	class CheckReaderStatus extends TimerTask {
		public void run() {

			synchronized (readerStatusList) {
				if (readerStatusListCurrent.size() != readerStatusList.size()) { // update number of reader: add or remove reader
					readerStatusListCurrent.clear();
					for (ReaderStatus readerStat : readerStatusList) {
						ReaderStatus readerStatus = new ReaderStatus();
						readerStatus.controllerName = readerStat.controllerName;
						readerStatus.ip = readerStat.ip;
						readerStatus.status = readerStat.status;

						readerStatusListCurrent.add(readerStatus);
					}
				}
				// check reader
				int res = 0;
				for (ReaderStatus readerStat : readerStatusListCurrent) {
					llRPmonitor = new LLRPmonitor();
					res = llRPmonitor.checkROSPEC(readerStat.ip);
					if (res == 1) {
						readerStat.status = true; // active
					} else {
						readerStat.status = false;// inactive
					}
				}
			}
			// update GUI
			updateGUI();
		}
	}

	/*
	 * =================
	 */
	public void updateGUI(){
		Thread timer = new Thread(new UpdateGUITimer());
		timer.start();  // run once 
	}
	
	class UpdateGUITimer implements Runnable  {
		public void run() {
			updateReaderTable(readerStatusListCurrent);
	    }		    		  
	}
	/*
	 * =================
	 */	  
	public void updateReaderTable(List<ReaderStatus> readerStatusList) {

		List<ReaderStatus> readerStatusListUpdate = new ArrayList<ReaderStatus>();
		ReaderStatus readerStatus = null;

		for (ReaderStatus readerStat : readerStatusList) {
			readerStatus = new ReaderStatus();
			readerStatus.controllerName = readerStat.controllerName;
			readerStatus.ip = readerStat.ip;
			readerStatus.status = readerStat.status;

			readerStatusListUpdate.add(readerStatus);
		}

		form.updateReaderTable(readerStatusListUpdate);
	}

	public void deleteReader(String IP) {
		ReaderStatus deletereader = null;

		synchronized (readerStatusList) {
			for (ReaderStatus readerStat : readerStatusList) {
				if (readerStat.ip.equals(IP)) {
					deletereader = readerStat;
					break;
				}
			}
			readerStatusList.remove(deletereader);
		}
		// delete current file
		boolean alreadyExists = new File(outputFile).exists();
		if (alreadyExists) {
			new File(outputFile).delete();
		}
		// write new file
		synchronized (readerStatusList) {
			for (ReaderStatus readerStat : readerStatusList) {
				AddReader(readerStat.controllerName, readerStat.ip);
			}
		}
	}

	public void stopCheck() {
		timer.cancel();		
	}
}
