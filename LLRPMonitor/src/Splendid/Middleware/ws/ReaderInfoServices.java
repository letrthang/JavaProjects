package Splendid.Middleware.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Splendid.Middleware.Form.CheckReader;
import Splendid.Middleware.Form.ReaderStatus;

@WebService
public class ReaderInfoServices {

	List<ReaderStatus> readerStatusListCurrent ;
	
	@WebMethod
	public String getVersion(){
		
		return "Impinj R248"; // for test
	}
	
	@WebMethod
	public List<ReaderStatus> getAllReaderInfo(){
		readerStatusListCurrent = new ArrayList<ReaderStatus>();
		for(ReaderStatus readerStatus : CheckReader.getReaderStatusListCurrent()){
			readerStatusListCurrent.add(readerStatus);
		}
		 
		return readerStatusListCurrent;
	}
}
