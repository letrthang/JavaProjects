/**
 * 
 */
package com.readrss.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.readrss.model.FeedMessage;

/**
 * @author Thang Le
 *
 */
public class FeedMessageDAO {

	/**
	 * 
	 */
	public FeedMessageDAO() {
		// TODO Auto-generated constructor stub
	}

	public void saveFeedMessage(FeedMessage message) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(message.getUiid() + ".txt"));
			out.write(message.getFullContent());

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
